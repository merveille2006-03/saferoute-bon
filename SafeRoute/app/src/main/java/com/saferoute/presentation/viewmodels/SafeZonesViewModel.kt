package com.saferoute.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saferoute.domain.model.LocationData
import com.saferoute.domain.model.SafeZone
import com.saferoute.domain.repository.LocationRepository
import com.saferoute.domain.repository.SafeZoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.pow
@HiltViewModel
class SafeZonesViewModel @Inject constructor(
    private val safeZoneRepository: SafeZoneRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val userId = "default_user"
    private val _safeZones = MutableStateFlow<List<SafeZone>>(emptyList())
    val safeZones: StateFlow<List<SafeZone>> = _safeZones.asStateFlow()

    private val _currentLocation = MutableStateFlow<LocationData?>(null)
    val currentLocation: StateFlow<LocationData?> = _currentLocation.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadSafeZones()
        loadCurrentLocation()
    }

    private fun loadSafeZones() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                safeZoneRepository.getAllSafeZonesFlow(userId).collect { list ->
                    _safeZones.value = list
                }
            } catch (e: Exception) {
                Timber.e(e, "Failed to load safe zones")
                _errorMessage.value = "Impossible de charger les zones sécurisées"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun loadCurrentLocation() {
        viewModelScope.launch {
            try {
                _currentLocation.value = locationRepository.getCurrentLocation()
            } catch (e: Exception) {
                Timber.e(e, "Failed to get current location")
            }
        }
    }

    fun addSafeZone(
        name: String,
        latitude: Double,
        longitude: Double,
        radius: Double = SafeZone.DEFAULT_RADIUS,
        type: String = SafeZone.TYPE_OTHER,
        alertOnExit: Boolean = true
    ) {
        viewModelScope.launch {
            try {
                val safeZone = SafeZone(
                    userId = userId,
                    name = name,
                    latitude = latitude,
                    longitude = longitude,
                    radius = radius.coerceIn(SafeZone.MIN_RADIUS, SafeZone.MAX_RADIUS),
                    type = type,
                    alertOnExit = alertOnExit
                )
                safeZoneRepository.addSafeZone(safeZone)
            } catch (e: Exception) {
                Timber.e(e, "Failed to add safe zone")
                _errorMessage.value = "Impossible d'ajouter la zone"
            }
        }
    }

    fun addSafeZoneAtCurrentLocation(
        name: String,
        radius: Double = SafeZone.DEFAULT_RADIUS,
        type: String = SafeZone.TYPE_OTHER
    ) {
        viewModelScope.launch {
            try {
                val location = locationRepository.getCurrentLocation()
                location?.let {
                    addSafeZone(name, it.latitude, it.longitude, radius, type)
                } ?: run {
                    _errorMessage.value = "Position actuelle non disponible"
                }
            } catch (e: Exception) {
                Timber.e(e, "Failed to add safe zone at current location")
                _errorMessage.value = "Impossible d'ajouter la zone"
            }
        }
    }

    fun updateSafeZone(safeZone: SafeZone) {
        viewModelScope.launch {
            try {
                safeZoneRepository.updateSafeZone(safeZone)
            } catch (e: Exception) {
                Timber.e(e, "Failed to update safe zone")
                _errorMessage.value = "Impossible de modifier la zone"
            }
        }
    }

    fun deleteSafeZone(safeZone: SafeZone) {
        viewModelScope.launch {
            try {
                safeZoneRepository.deleteSafeZone(safeZone)
            } catch (e: Exception) {
                Timber.e(e, "Failed to delete safe zone")
                _errorMessage.value = "Impossible de supprimer la zone"
            }
        }
    }

    fun toggleSafeZoneActive(safeZone: SafeZone) {
        viewModelScope.launch {
            try {
                safeZoneRepository.updateSafeZone(safeZone.copy(isActive = !safeZone.isActive))
            } catch (e: Exception) {
                Timber.e(e, "Failed to toggle safe zone")
            }
        }
    }

    fun isLocationInSafeZone(location: LocationData): SafeZone? {
        return _safeZones.value.find { zone ->
            calculateDistance(location, zone) <= zone.radius
        }
    }

    private fun calculateDistance(location: LocationData, zone: SafeZone): Double {
        val earthRadius = 6371000.0

        val lat1 = Math.toRadians(location.latitude)
        val lat2 = Math.toRadians(zone.latitude)
        val deltaLat = Math.toRadians(zone.latitude - location.latitude)
        val deltaLon = Math.toRadians(zone.longitude - location.longitude)

        val a = kotlin.math.sin(deltaLat / 2).pow(2) +
                kotlin.math.cos(lat1) * kotlin.math.cos(lat2) *
                kotlin.math.sin(deltaLon / 2).pow(2)
        val c = 2 * kotlin.math.atan2(kotlin.math.sqrt(a), kotlin.math.sqrt(1 - a))

        return earthRadius * c
    }

    fun refreshLocation() {
        loadCurrentLocation()
    }

    fun clearError() {
        _errorMessage.value = null
    }
}