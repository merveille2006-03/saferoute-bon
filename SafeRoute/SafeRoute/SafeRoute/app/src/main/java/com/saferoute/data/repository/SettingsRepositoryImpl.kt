package com.saferoute.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.saferoute.domain.model.AppSettings
import com.saferoute.domain.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SettingsRepository {

    private val dataStore = context.dataStore

    private object PreferencesKeys {
        val USER_ID = stringPreferencesKey("user_id")
        val FALL_DETECTION = booleanPreferencesKey("fall_detection_enabled")
        val LOCATION_TRACKING = booleanPreferencesKey("location_tracking_enabled")
        val SAFE_ZONE_MONITORING = booleanPreferencesKey("safe_zone_monitoring_enabled")
        val BLUETOOTH_ENABLED = booleanPreferencesKey("bluetooth_enabled")
        val BIOMETRIC_REQUIRED = booleanPreferencesKey("biometric_required")
        val FALL_SENSITIVITY = intPreferencesKey("fall_sensitivity")
        val SOS_COUNTDOWN = intPreferencesKey("sos_countdown")
        val AUTO_SEND = booleanPreferencesKey("auto_send_no_response")
        val INCLUDE_LOCATION = booleanPreferencesKey("include_location")
        val SOUND_ENABLED = booleanPreferencesKey("sound_enabled")
        val VIBRATION_ENABLED = booleanPreferencesKey("vibration_enabled")
        val TRACKING_INTERVAL = longPreferencesKey("tracking_interval")
        val BLUETOOTH_DEVICE = stringPreferencesKey("bluetooth_device_id")
    }

    override suspend fun getSettings(userId: String): AppSettings {
        return dataStore.data.first().toAppSettings(userId)
    }

    override fun getSettingsFlow(userId: String): Flow<AppSettings> {
        return dataStore.data.map { preferences ->
            preferences.toAppSettings(userId)
        }
    }

    override suspend fun saveSettings(settings: AppSettings) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_ID] = settings.userId
            preferences[PreferencesKeys.FALL_DETECTION] = settings.isFallDetectionEnabled
            preferences[PreferencesKeys.LOCATION_TRACKING] = settings.isLocationTrackingEnabled
            preferences[PreferencesKeys.SAFE_ZONE_MONITORING] = settings.isSafeZoneMonitoringEnabled
            preferences[PreferencesKeys.BLUETOOTH_ENABLED] = settings.isBluetoothEnabled
            preferences[PreferencesKeys.BIOMETRIC_REQUIRED] = settings.isBiometricRequired
            preferences[PreferencesKeys.FALL_SENSITIVITY] = settings.fallDetectionSensitivity
            preferences[PreferencesKeys.SOS_COUNTDOWN] = settings.sosCountdownSeconds
            preferences[PreferencesKeys.AUTO_SEND] = settings.autoSendOnNoResponse
            preferences[PreferencesKeys.INCLUDE_LOCATION] = settings.includeLocationInAlerts
            preferences[PreferencesKeys.SOUND_ENABLED] = settings.soundEnabled
            preferences[PreferencesKeys.VIBRATION_ENABLED] = settings.vibrationEnabled
            preferences[PreferencesKeys.TRACKING_INTERVAL] = settings.trackingIntervalMs
            settings.bluetoothDeviceId?.let { preferences[PreferencesKeys.BLUETOOTH_DEVICE] = it }
        }
    }

    override suspend fun updateSettings(userId: String, update: (AppSettings) -> AppSettings) {
        val currentSettings = getSettings(userId)
        saveSettings(update(currentSettings))
    }

    override suspend fun resetSettings(userId: String) {
        saveSettings(AppSettings(userId = userId))
    }

    private fun Preferences.toAppSettings(userId: String): AppSettings {
        return AppSettings(
            userId = this[PreferencesKeys.USER_ID] ?: userId,
            isFallDetectionEnabled = this[PreferencesKeys.FALL_DETECTION] ?: true,
            isLocationTrackingEnabled = this[PreferencesKeys.LOCATION_TRACKING] ?: true,
            isSafeZoneMonitoringEnabled = this[PreferencesKeys.SAFE_ZONE_MONITORING] ?: true,
            isBluetoothEnabled = this[PreferencesKeys.BLUETOOTH_ENABLED] ?: true,
            isBiometricRequired = this[PreferencesKeys.BIOMETRIC_REQUIRED] ?: false,
            fallDetectionSensitivity = this[PreferencesKeys.FALL_SENSITIVITY] ?: AppSettings.SENSITIVITY_MEDIUM,
            sosCountdownSeconds = this[PreferencesKeys.SOS_COUNTDOWN] ?: 5,
            autoSendOnNoResponse = this[PreferencesKeys.AUTO_SEND] ?: true,
            includeLocationInAlerts = this[PreferencesKeys.INCLUDE_LOCATION] ?: true,
            soundEnabled = this[PreferencesKeys.SOUND_ENABLED] ?: true,
            vibrationEnabled = this[PreferencesKeys.VIBRATION_ENABLED] ?: true,
            trackingIntervalMs = this[PreferencesKeys.TRACKING_INTERVAL] ?: 10000L,
            bluetoothDeviceId = this[PreferencesKeys.BLUETOOTH_DEVICE]
        )
    }
}