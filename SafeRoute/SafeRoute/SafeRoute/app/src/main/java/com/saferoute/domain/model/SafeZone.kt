package com.saferoute.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "safe_zones")
data class SafeZone(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val radius: Double, // in meters
    val type: String,
    val isActive: Boolean = true,
    val alertOnExit: Boolean = true,
    val alertOnEnter: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) {
    companion object {
        const val TYPE_HOME = "home"
        const val TYPE_WORK = "work"
        const val TYPE_SCHOOL = "school"
        const val TYPE_OTHER = "other"
        
        // Default radius in meters
        const val DEFAULT_RADIUS = 200.0
        const val MIN_RADIUS = 50.0
        const val MAX_RADIUS = 1000.0
    }
}
