package com.saferoute.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "fall_events")
data class FallEvent(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val timestamp: Long = System.currentTimeMillis(),
    val location: LocationData? = null,
    val impactForce: Double,
    val fallDuration: Long, // in milliseconds
    val isConfirmed: Boolean? = null, // null = pending, true = confirmed fall, false = false alarm
    val responseTime: Long? = null, // time until user response in milliseconds
    val alertSent: Boolean = false,
    val alertSentAt: Long? = null,
    val notes: String? = null
) {
    companion object {
        const val RESPONSE_TIMEOUT_MS = 30000L // 30 seconds
        const val MIN_IMPACT_FORCE = 2.5 // G-force threshold
        const val FALL_DURATION_THRESHOLD = 200 // minimum fall duration in ms
    }
}

enum class FallDetectionState {
    IDLE,
    DETECTING,
    CONFIRMATION_REQUIRED,
    CONFIRMED,
    CANCELLED,
    ALERT_SENT
}
