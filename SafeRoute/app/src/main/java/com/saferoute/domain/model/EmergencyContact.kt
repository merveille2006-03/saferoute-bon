package com.saferoute.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "emergency_contacts")
data class EmergencyContact(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val name: String,
    val phoneNumber: String,
    val email: String? = null,
    val relationship: String,
    val priority: Int = 1, // 1 = highest priority
    val isEnabled: Boolean = true,
    val notifyOnFall: Boolean = true,
    val notifyOnSOS: Boolean = true,
    val notifyOnSafeZoneExit: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
) {
    companion object {
        val RELATIONSHIP_FAMILY = "family"
        val RELATIONSHIP_FRIEND = "friend"
        val RELATIONSHIP_COLLEAGUE = "colleague"
        val RELATIONSHIP_OTHER = "other"
    }
}
