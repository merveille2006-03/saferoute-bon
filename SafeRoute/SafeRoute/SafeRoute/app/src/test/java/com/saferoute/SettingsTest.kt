package com.saferoute

import com.saferoute.domain.model.AppSettings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SettingsTest {

    @Test
    @DisplayName("Default settings should have all features enabled")
    fun defaultSettings_allEnabled() {
        val settings = AppSettings(userId = "test_user")

        assertTrue(settings.isFallDetectionEnabled)
        assertTrue(settings.isLocationTrackingEnabled)
        assertTrue(settings.isSafeZoneMonitoringEnabled)
        assertTrue(settings.isBluetoothEnabled)
    }

    @Test
    @DisplayName("Default settings should have biometric disabled")
    fun defaultSettings_biometricDisabled() {
        val settings = AppSettings(userId = "test_user")

        assertFalse(settings.isBiometricRequired)
    }

    @Test
    @DisplayName("Default sensitivity should be medium")
    fun defaultSettings_sensitivity() {
        val settings = AppSettings(userId = "test_user")

        assertEquals(AppSettings.SENSITIVITY_MEDIUM, settings.fallDetectionSensitivity)
    }

    @Test
    @DisplayName("Default SOS countdown should be 5 seconds")
    fun defaultSettings_sosCountdown() {
        val settings = AppSettings(userId = "test_user")

        assertEquals(5, settings.sosCountdownSeconds)
    }

    @Test
    @DisplayName("Settings should be copyable with modified values")
    fun settings_copy() {
        val original = AppSettings(userId = "test_user")

        val modified = original.copy(
            isFallDetectionEnabled = false,
            fallDetectionSensitivity = AppSettings.SENSITIVITY_HIGH
        )

        assertEquals(original.userId, modified.userId)
        assertFalse(modified.isFallDetectionEnabled)
        assertEquals(AppSettings.SENSITIVITY_HIGH, modified.fallDetectionSensitivity)
        assertTrue(modified.isLocationTrackingEnabled) // Unchanged
    }

    @Test
    @DisplayName("Sensitivity levels should be correctly defined")
    fun sensitivity_levels() {
        assertEquals(1, AppSettings.SENSITIVITY_LOW)
        assertEquals(2, AppSettings.SENSITIVITY_MEDIUM)
        assertEquals(3, AppSettings.SENSITIVITY_HIGH)
    }

    @Test
    @DisplayName("Tracking interval should default to 10 seconds")
    fun defaultSettings_trackingInterval() {
        val settings = AppSettings(userId = "test_user")

        assertEquals(10000L, settings.trackingIntervalMs)
    }
}