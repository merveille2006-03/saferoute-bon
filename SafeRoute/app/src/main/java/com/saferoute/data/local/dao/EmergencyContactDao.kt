package com.saferoute.data.local.dao

import androidx.room.*
import com.saferoute.data.local.entity.EmergencyContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EmergencyContactDao {
    @Query("SELECT * FROM emergency_contacts WHERE userId = :userId ORDER BY priority ASC, createdAt DESC")
    suspend fun getAllContacts(userId: String): List<EmergencyContactEntity>

    @Query("SELECT * FROM emergency_contacts WHERE userId = :userId ORDER BY priority ASC, createdAt DESC")
    fun getAllContactsFlow(userId: String): Flow<List<EmergencyContactEntity>>

    @Query("SELECT * FROM emergency_contacts WHERE id = :contactId")
    suspend fun getContactById(contactId: Long): EmergencyContactEntity?

    @Query("SELECT * FROM emergency_contacts WHERE userId = :userId AND isEnabled = 1 ORDER BY priority ASC")
    suspend fun getEnabledContacts(userId: String): List<EmergencyContactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: EmergencyContactEntity): Long

    @Update
    suspend fun updateContact(contact: EmergencyContactEntity)

    @Delete
    suspend fun deleteContact(contact: EmergencyContactEntity)

    @Query("DELETE FROM emergency_contacts WHERE id = :contactId")
    suspend fun deleteContactById(contactId: Long)

    @Query("DELETE FROM emergency_contacts WHERE userId = :userId")
    suspend fun deleteAllContacts(userId: String)
}