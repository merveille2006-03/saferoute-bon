package com.saferoute.data.repository

import com.saferoute.data.local.dao.EmergencyContactDao
import com.saferoute.data.local.entity.EmergencyContactEntity
import com.saferoute.domain.model.EmergencyContact
import com.saferoute.domain.repository.EmergencyContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmergencyContactRepositoryImpl @Inject constructor(
    private val contactDao: EmergencyContactDao
) : EmergencyContactRepository {

    override suspend fun getAllContacts(userId: String): List<EmergencyContact> {
        return contactDao.getAllContacts(userId).map { it.toDomainModel() }
    }

    override fun getAllContactsFlow(userId: String): Flow<List<EmergencyContact>> {
        return contactDao.getAllContactsFlow(userId).map { list ->
            list.map { it.toDomainModel() }
        }
    }

    override suspend fun getContactById(contactId: Long): EmergencyContact? {
        return contactDao.getContactById(contactId)?.toDomainModel()
    }

    override suspend fun addContact(contact: EmergencyContact): Long {
        return contactDao.insertContact(EmergencyContactEntity.fromDomainModel(contact))
    }

    override suspend fun updateContact(contact: EmergencyContact) {
        contactDao.updateContact(EmergencyContactEntity.fromDomainModel(contact))
    }

    override suspend fun deleteContact(contact: EmergencyContact) {
        contactDao.deleteContact(EmergencyContactEntity.fromDomainModel(contact))
    }

    override suspend fun deleteContactById(contactId: Long) {
        contactDao.deleteContactById(contactId)
    }

    override suspend fun getEnabledContacts(userId: String): List<EmergencyContact> {
        return contactDao.getEnabledContacts(userId).map { it.toDomainModel() }
    }

    override suspend fun reorderContacts(contacts: List<EmergencyContact>) {
        contacts.forEachIndexed { index, contact ->
            contactDao.updateContact(
                EmergencyContactEntity.fromDomainModel(contact.copy(priority = index + 1))
            )
        }
    }
}