package com.saferoute.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.saferoute.domain.model.EmergencyContact

@Entity(
    tableName = "emergency_contacts",
    indices = [Index(value = ["userId"])]
)
data class EmergencyContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val name: String,
    val phoneNumber: String,
    val email: String?,
    val relationship: String,
    val priority: Int,
    val isEnabled: Boolean,
    val notifyOnFall: Boolean,
    val notifyOnSOS: Boolean,
    val notifyOnSafeZoneExit: Boolean,
    val createdAt: Long
) {
    fun toDomainModel(): EmergencyContact = EmergencyContact(
        id = id,
        userId = userId,
        name = name,
        phoneNumber = phoneNumber,
        email = email,
        relationship = relationship,
        priority = priority,
        isEnabled = isEnabled,
        notifyOnFall = notifyOnFall,
        notifyOnSOS = notifyOnSOS,
        notifyOnSafeZoneExit = notifyOnSafeZoneExit,
        createdAt = createdAt
    )

    companion object {
        fun fromDomainModel(contact: EmergencyContact): EmergencyContactEntity = EmergencyContactEntity(
            id = contact.id,
            userId = contact.userId,
            name = contact.name,
            phoneNumber = contact.phoneNumber,
            email = contact.email,
            relationship = contact.relationship,
            priority = contact.priority,
            isEnabled = contact.isEnabled,
            notifyOnFall = contact.notifyOnFall,
            notifyOnSOS = contact.notifyOnSOS,
            notifyOnSafeZoneExit = contact.notifyOnSafeZoneExit,
            createdAt = contact.createdAt
        )
    }
}