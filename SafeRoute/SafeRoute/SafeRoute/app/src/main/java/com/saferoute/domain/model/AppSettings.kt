package com.saferoute.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val userId: String = "",
    val isFallDetectionEnabled: Boolean = true,
    val isLocationTrackingEnabled: Boolean = true,
    val isSafeZoneMonitoringEnabled: Boolean = true,
    val isBluetoothEnabled: Boolean = true,
    val isBiometricRequired: Boolean = false,
    val fallDetectionSensitivity: Int = SENSITIVITY_MEDIUM,
    val sosCountdownSeconds: Int = 5,
    val autoSendOnNoResponse: Boolean = true,
    val includeLocationInAlerts: Boolean = true,
    val soundEnabled: Boolean = true,
    val vibrationEnabled: Boolean = true,
    val trackingIntervalMs: Long = 10000L, // 10 seconds
    val bluetoothDeviceId: String? = null
) {
    companion object {
        const val SENSITIVITY_LOW = 1
        const val SENSITIVITY_MEDIUM = 2
        const val SENSITIVITY_HIGH = 3
        
        fun getImpactThreshold(sensitivity: Int): Double {
            return when (sensitivity) {
                SENSITIVITY_LOW -> 3.5
                SENSITIVITY_HIGH -> 1.5
                else -> 2.5 // MEDIUM
            }
        }
    }
}
