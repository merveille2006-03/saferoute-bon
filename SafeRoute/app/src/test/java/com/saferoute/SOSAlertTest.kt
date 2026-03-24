package com.saferoute

import com.saferoute.domain.model.LocationData
import com.saferoute.domain.model.SOSAlert
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SOSAlertTest {

    @Test
    @DisplayName("SOS alert should have correct default status")
    fun sosAlert_defaultStatus() {
        val alert = SOSAlert(
            userId = "test_user",
            triggerType = SOSAlert.TRIGGER_MANUAL,
            message = "Test alert",
            recipients = listOf("+33612345678")
        )

        assertEquals(SOSAlert.STATUS_PENDING, alert.status)
        assertEquals(0, alert.deliveredCount)
        assertEquals(0, alert.failedCount)
    }

    @Test
    @DisplayName("Default SOS message should contain user name and location")
    fun sosAlert_defaultMessage() {
        val location = LocationData(
            latitude = 48.8566,
            longitude = 2.3522,
            accuracy = 10f,
            altitude = 0.0,
            speed = 0f,
            bearing = 0f
        )

        val message = SOSAlert.createDefaultMessage("John Doe", location)

        assertTrue(message.contains("ALERTE SAFEROUTE"))
        assertTrue(message.contains("John Doe"))
        assertTrue(message.contains("48.8566"))
        assertTrue(message.contains("2.3522"))
    }

    @Test
    @DisplayName("SOS alert without location should still create valid message")
    fun sosAlert_messageWithoutLocation() {
        val message = SOSAlert.createDefaultMessage("John Doe", null)

        assertTrue(message.contains("ALERTE SAFEROUTE"))
        assertTrue(message.contains("John Doe"))
    }

    @Test
    @DisplayName("Trigger types should be correctly defined")
    fun sosAlert_triggerTypes() {
        assertEquals("manual", SOSAlert.TRIGGER_MANUAL)
        assertEquals("fall_detection", SOSAlert.TRIGGER_FALL_DETECTION)
        assertEquals("safe_zone_exit", SOSAlert.TRIGGER_SAFE_ZONE_EXIT)
        assertEquals("bluetooth", SOSAlert.TRIGGER_BLUETOOTH)
    }

    @Test
    @DisplayName("Status constants should be correctly defined")
    fun sosAlert_statusConstants() {
        assertEquals("pending", SOSAlert.STATUS_PENDING)
        assertEquals("sent", SOSAlert.STATUS_SENT)
        assertEquals("delivered", SOSAlert.STATUS_DELIVERED)
        assertEquals("failed", SOSAlert.STATUS_FAILED)
    }
}