package com.saferoute.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saferoute.domain.model.AppSettings
import com.saferoute.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val userId = "default_user"

    private val _settings = MutableStateFlow(AppSettings(userId = userId))
    val settings: StateFlow<AppSettings> = _settings.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                settingsRepository.getSettingsFlow(userId).collect { settings ->
                    _settings.value = settings
                }
            } catch (e: Exception) {
                Timber.e(e, "Failed to load settings")
                _errorMessage.value = "Impossible de charger les paramètres"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateFallDetectionEnabled(enabled: Boolean) {
        updateSettings { it.copy(isFallDetectionEnabled = enabled) }
    }

    fun updateLocationTrackingEnabled(enabled: Boolean) {
        updateSettings { it.copy(isLocationTrackingEnabled = enabled) }
    }

    fun updateSafeZoneMonitoringEnabled(enabled: Boolean) {
        updateSettings { it.copy(isSafeZoneMonitoringEnabled = enabled) }
    }

    fun updateBluetoothEnabled(enabled: Boolean) {
        updateSettings { it.copy(isBluetoothEnabled = enabled) }
    }

    fun updateBiometricRequired(required: Boolean) {
        updateSettings { it.copy(isBiometricRequired = required) }
    }

    fun updateFallDetectionSensitivity(sensitivity: Int) {
        updateSettings { it.copy(fallDetectionSensitivity = sensitivity) }
    }

    fun updateSOSCountdown(seconds: Int) {
        updateSettings { it.copy(sosCountdownSeconds = seconds.coerceIn(3, 10)) }
    }

    fun updateAutoSendOnNoResponse(enabled: Boolean) {
        updateSettings { it.copy(autoSendOnNoResponse = enabled) }
    }

    fun updateIncludeLocationInAlerts(enabled: Boolean) {
        updateSettings { it.copy(includeLocationInAlerts = enabled) }
    }

    fun updateSoundEnabled(enabled: Boolean) {
        updateSettings { it.copy(soundEnabled = enabled) }
    }

    fun updateVibrationEnabled(enabled: Boolean) {
        updateSettings { it.copy(vibrationEnabled = enabled) }
    }

    fun updateTrackingInterval(intervalMs: Long) {
        updateSettings { it.copy(trackingIntervalMs = intervalMs.coerceIn(5000, 60000)) }
    }

    fun resetSettings() {
        viewModelScope.launch {
            try {
                settingsRepository.resetSettings(userId)
            } catch (e: Exception) {
                Timber.e(e, "Failed to reset settings")
                _errorMessage.value = "Impossible de réinitialiser les paramètres"
            }
        }
    }

    private fun updateSettings(update: (AppSettings) -> AppSettings) {
        viewModelScope.launch {
            try {
                settingsRepository.updateSettings(userId, update)
            } catch (e: Exception) {
                Timber.e(e, "Failed to update settings")
                _errorMessage.value = "Impossible de mettre à jour les paramètres"
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}