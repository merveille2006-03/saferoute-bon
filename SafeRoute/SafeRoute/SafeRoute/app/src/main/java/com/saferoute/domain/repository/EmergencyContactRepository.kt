package com.saferoute.domain.repository

import com.saferoute.domain.model.EmergencyContact
import kotlinx.coroutines.flow.Flow

interface EmergencyContactRepository {
    suspend fun getAllContacts(userId: String): List<EmergencyContact>
    fun getAllContactsFlow(userId: String): Flow<List<EmergencyContact>>
    suspend fun getContactById(contactId: Long): EmergencyContact?
    suspend fun addContact(contact: EmergencyContact): Long
    suspend fun updateContact(contact: EmergencyContact)
    suspend fun deleteContact(contact: EmergencyContact)
    suspend fun deleteContactById(contactId: Long)
    suspend fun getEnabledContacts(userId: String): List<EmergencyContact>
    suspend fun reorderContacts(contacts: List<EmergencyContact>)
}