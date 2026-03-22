package com.saferoute

import com.saferoute.domain.model.LocationData
import com.saferoute.domain.model.SafeZone
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SafeZoneTest {

    @Test
    @DisplayName("Safe zone should have correct default radius")
    fun safeZone_defaultRadius() {
        assertEquals(200.0, SafeZone.DEFAULT_RADIUS, 0.01)
    }

    @Test
    @DisplayName("Safe zone radius should be within valid range")
    fun safeZone_radiusRange() {
        assertEquals(50.0, SafeZone.MIN_RADIUS, 0.01)
        assertEquals(1000.0, SafeZone.MAX_RADIUS, 0.01)
    }

    @Test
    @DisplayName("Safe zone should be active by default")
    fun safeZone_defaultActive() {
        val zone = SafeZone(
            userId = "test_user",
            name = "Home",
            latitude = 48.8566,
            longitude = 2.3522,
            radius = 200.0,
            type = SafeZone.TYPE_HOME
        )

        assertTrue(zone.isActive)
        assertTrue(zone.alertOnExit)
        assertFalse(zone.alertOnEnter)
    }

    @Test
    @DisplayName("Location data should be valid")
    fun locationData_valid() {
        val validLocation = LocationData(
            latitude = 48.8566,
            longitude = 2.3522,
            accuracy = 10f,
            altitude = 0.0,
            speed = 0f,
            bearing = 0f
        )

        assertTrue(validLocation.isValid())
    }

    @Test
    @DisplayName("Invalid location data should be detected")
    fun locationData_invalid() {
        val invalidLocation = LocationData(
            latitude = 0.0,
            longitude = 0.0,
            accuracy = 10f,
            altitude = 0.0,
            speed = 0f,
            bearing = 0f
        )

        assertFalse(invalidLocation.isValid())
    }

    @Test
    @DisplayName("Location data should convert to LatLng pair")
    fun locationData_toLatLng() {
        val location = LocationData(
            latitude = 48.8566,
            longitude = 2.3522,
            accuracy = 10f,
            altitude = 0.0,
            speed = 0f,
            bearing = 0f
        )

        val (lat, lng) = location.toLatLng()
        assertEquals(48.8566, lat, 0.0001)
        assertEquals(2.3522, lng, 0.0001)
    }
}