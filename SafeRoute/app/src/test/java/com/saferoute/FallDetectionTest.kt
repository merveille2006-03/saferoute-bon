package com.saferoute

import com.saferoute.domain.model.FallEvent
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class FallDetectionTest {

    @Test
    @DisplayName("Fall event should have correct default values")
    fun fallEvent_defaultValues() {
        val event = FallEvent(
            userId = "test_user",
            impactForce = 3.0,
            fallDuration = 300
        )

        assertEquals("test_user", event.userId)
        assertEquals(3.0, event.impactForce, 0.01)
        assertEquals(300, event.fallDuration)
        assertNull(event.isConfirmed)
        assertFalse(event.alertSent)
    }

    @ParameterizedTest
    @DisplayName("Impact threshold should vary by sensitivity level")
    @CsvSource(
        "1, 3.5",   // Low sensitivity
        "2, 2.5",   // Medium sensitivity
        "3, 1.5"    // High sensitivity
    )
    fun impactThreshold_bySensitivity(sensitivity: Int, expectedThreshold: Double) {
        val threshold = com.saferoute.domain.model.AppSettings.getImpactThreshold(sensitivity)
        assertEquals(expectedThreshold, threshold, 0.01)
    }

    @Test
    @DisplayName("Response timeout should be 30 seconds")
    fun responseTimeout_constant() {
        assertEquals(30000L, FallEvent.RESPONSE_TIMEOUT_MS)
    }

    @Test
    @DisplayName("Fall event should be copyable with modified values")
    fun fallEvent_copy() {
        val original = FallEvent(
            userId = "test_user",
            impactForce = 3.0,
            fallDuration = 300
        )

        val copy = original.copy(
            isConfirmed = true,
            alertSent = true
        )

        assertEquals(original.userId, copy.userId)
        assertEquals(original.impactForce, copy.impactForce, 0.01)
        assertTrue(copy.isConfirmed!!)
        assertTrue(copy.alertSent)
    }
}