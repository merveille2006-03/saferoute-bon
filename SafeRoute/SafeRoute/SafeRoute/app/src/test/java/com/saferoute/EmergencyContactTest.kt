package com.saferoute

import com.saferoute.domain.model.EmergencyContact
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class EmergencyContactTest {

    @Test
    @DisplayName("Emergency contact should have correct default values")
    fun emergencyContact_defaultValues() {
        val contact = EmergencyContact(
            userId = "test_user",
            name = "John Doe",
            phoneNumber = "+33612345678",
            relationship = EmergencyContact.RELATIONSHIP_FAMILY
        )

        assertEquals("test_user", contact.userId)
        assertEquals("John Doe", contact.name)
        assertEquals("+33612345678", contact.phoneNumber)
        assertEquals(EmergencyContact.RELATIONSHIP_FAMILY, contact.relationship)
        assertEquals(1, contact.priority)
        assertTrue(contact.isEnabled)
        assertTrue(contact.notifyOnFall)
        assertTrue(contact.notifyOnSOS)
        assertTrue(contact.notifyOnSafeZoneExit)
    }

    @Test
    @DisplayName("Relationship types should be correctly defined")
    fun emergencyContact_relationshipTypes() {
        assertEquals("family", EmergencyContact.RELATIONSHIP_FAMILY)
        assertEquals("friend", EmergencyContact.RELATIONSHIP_FRIEND)
        assertEquals("colleague", EmergencyContact.RELATIONSHIP_COLLEAGUE)
        assertEquals("other", EmergencyContact.RELATIONSHIP_OTHER)
    }

    @Test
    @DisplayName("Emergency contact should be copyable with modified values")
    fun emergencyContact_copy() {
        val original = EmergencyContact(
            userId = "test_user",
            name = "John Doe",
            phoneNumber = "+33612345678",
            relationship = EmergencyContact.RELATIONSHIP_FAMILY
        )

        val copy = original.copy(
            isEnabled = false,
            priority = 2
        )

        assertEquals(original.name, copy.name)
        assertFalse(copy.isEnabled)
        assertEquals(2, copy.priority)
    }
}