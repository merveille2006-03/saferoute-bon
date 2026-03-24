package com.saferoute.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.saferoute.domain.model.FallEvent
import com.saferoute.domain.model.LocationData

@Entity(
    tableName = "fall_events",
    indices = [Index(value = ["userId"]), Index(value = ["timestamp"])]
)
data class FallEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val timestamp: Long,
    val location: LocationData?,
    val impactForce: Double,
    val fallDuration: Long,
    val isConfirmed: Boolean?,
    val responseTime: Long?,
    val alertSent: Boolean,
    val alertSentAt: Long?,
    val notes: String?
) {
    fun toDomainModel(): FallEvent = FallEvent(
        id = id,
        userId = userId,
        timestamp = timestamp,
        location = location,
        impactForce = impactForce,
        fallDuration = fallDuration,
        isConfirmed = isConfirmed,
        responseTime = responseTime,
        alertSent = alertSent,
        alertSentAt = alertSentAt,
        notes = notes
    )

    companion object {
        fun fromDomainModel(event: FallEvent): FallEventEntity = FallEventEntity(
            id = event.id,
            userId = event.userId,
            timestamp = event.timestamp,
            location = event.location,
            impactForce = event.impactForce,
            fallDuration = event.fallDuration,
            isConfirmed = event.isConfirmed,
            responseTime = event.responseTime,
            alertSent = event.alertSent,
            alertSentAt = event.alertSentAt,
            notes = event.notes
        )
    }
}