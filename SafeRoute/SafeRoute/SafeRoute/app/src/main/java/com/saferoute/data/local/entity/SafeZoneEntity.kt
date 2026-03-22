package com.saferoute.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.saferoute.domain.model.SafeZone

@Entity(
    tableName = "safe_zones",
    indices = [Index(value = ["userId"])]
)
data class SafeZoneEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val radius: Double,
    val type: String,
    val isActive: Boolean,
    val alertOnExit: Boolean,
    val alertOnEnter: Boolean,
    val createdAt: Long
) {
    fun toDomainModel(): SafeZone = SafeZone(
        id = id,
        userId = userId,
        name = name,
        latitude = latitude,
        longitude = longitude,
        radius = radius,
        type = type,
        isActive = isActive,
        alertOnExit = alertOnExit,
        alertOnEnter = alertOnEnter,
        createdAt = createdAt
    )

    companion object {
        fun fromDomainModel(zone: SafeZone): SafeZoneEntity = SafeZoneEntity(
            id = zone.id,
            userId = zone.userId,
            name = zone.name,
            latitude = zone.latitude,
            longitude = zone.longitude,
            radius = zone.radius,
            type = zone.type,
            isActive = zone.isActive,
            alertOnExit = zone.alertOnExit,
            alertOnEnter = zone.alertOnEnter,
            createdAt = zone.createdAt
        )
    }
}