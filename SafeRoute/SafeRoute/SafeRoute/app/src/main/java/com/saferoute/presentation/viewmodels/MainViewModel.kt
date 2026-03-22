package com.saferoute.presentation.viewmodels

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.saferoute.domain.model.*
import com.saferoute.domain.repository.*
import com.saferoute.service.FallDetectionService
import com.saferoute.service.LocationTrackingService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val settingsRepository: SettingsRepository,
    private val locationRepository: LocationRepository,
    private val fallDetectionRepository: FallDetectionRepository,
    private val safeZoneRepository: SafeZoneRepository,
    private val alertRepository: AlertRepository,
    private val contactRepository: EmergencyContactRepository
) : AndroidViewModel(application) {

    private val context = application.applicationContext

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _currentLocation = MutableStateFlow<LocationData?>(null)
    val currentLocation: StateFlow<LocationData?> = _currentLocation.asStateFlow()

    private val _fallDetectionState = MutableStateFlow(FallDetectionState.IDLE)
    val fallDetectionState: StateFlow<FallDetectionState> = _fallDetectionState.asStateFlow()

    private val _safeZones = MutableStateFlow<List<SafeZone>>(emptyList())
    val safeZones: StateFlow<List<SafeZone>> = _safeZones.asStateFlow()

    private val _isServiceRunning = MutableStateFlow(false)
    val isServiceRunning: StateFlow<Boolean> = _isServiceRunning.asStateFlow()

    private val _alertCount = MutableStateFlow(0)
    val alertCount: StateFlow<Int> = _alertCount.asStateFlow()

    private val userId = "default_user"

    init {
        viewModelScope.launch {
            initializeApp()
        }
    }

    private suspend fun initializeApp() {
        try {
            val settings = settingsRepository.getSettings(userId)

            // Load safe zones
            _safeZones.value = safeZoneRepository.getActiveSafeZones(userId)

            // Start services if enabled
            if (settings.isFallDetectionEnabled) {
                startFallDetectionService()
            }

            if (settings.isLocationTrackingEnabled) {
                startLocationTrackingService()
            }

            // Collect location updates
            if (locationRepository.isLocationPermissionGranted()) {
                locationRepository.getLocationUpdates(settings.trackingIntervalMs)
                    .collect { location ->
                        _currentLocation.value = location
                        checkSafeZones(location)
                    }
            }

        } catch (e: Exception) {
            Timber.e(e, "Failed to initialize app")
        } finally {
            _isLoading.value = false
        }
    }

    private suspend fun checkSafeZones(location: LocationData) {
        val exitedZone = safeZoneRepository.checkSafeZoneExit(location, userId)
        exitedZone?.let { zone ->
            if (settingsRepository.getSettings(userId).isSafeZoneMonitoringEnabled) {
                sendSafeZoneAlert(location, zone)
            }
        }
    }

    private suspend fun sendSafeZoneAlert(location: LocationData, zone: SafeZone) {
        val message = "ALERTE SAFEROUTE: Sortie de la zone '${zone.name}'. " +
                "https://maps.google.com/?q=${location.latitude},${location.longitude}"

        alertRepository.sendSOSAlert(
            userId = userId,
            location = location,
            triggerType = SOSAlert.TRIGGER_SAFE_ZONE_EXIT,
            customMessage = message
        )
    }

    fun startFallDetectionService() {
        val intent = Intent(context, FallDetectionService::class.java).apply {
            action = FallDetectionService.ACTION_START
        }
        context.startForegroundService(intent)
        _isServiceRunning.value = true
    }

    fun stopFallDetectionService() {
        val intent = Intent(context, FallDetectionService::class.java).apply {
            action = FallDetectionService.ACTION_STOP
        }
        context.stopService(intent)
        _isServiceRunning.value = false
    }

    fun startLocationTrackingService() {
        val intent = Intent(context, LocationTrackingService::class.java).apply {
            action = LocationTrackingService.ACTION_START
        }
        context.startForegroundService(intent)
    }

    fun stopLocationTrackingService() {
        val intent = Intent(context, LocationTrackingService::class.java).apply {
            action = LocationTrackingService.ACTION_STOP
        }
        context.stopService(intent)
    }

    fun sendSOSAlert() {
        viewModelScope.launch {
            val location = _currentLocation.value
            alertRepository.sendSOSAlert(
                userId = userId,
                location = location,
                triggerType = SOSAlert.TRIGGER_MANUAL
            )
            _alertCount.value++
        }
    }

    fun confirmFall(isRealFall: Boolean) {
        viewModelScope.launch {
            _currentFallEvent.value?.let { event ->
                fallDetectionRepository.confirmFall(event.id, isRealFall)
            }
        }
    }

    private val _currentFallEvent = MutableStateFlow<FallEvent?>(null)
    val currentFallEvent: StateFlow<FallEvent?> = _currentFallEvent.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        stopFallDetectionService()
        stopLocationTrackingService()
    }
}