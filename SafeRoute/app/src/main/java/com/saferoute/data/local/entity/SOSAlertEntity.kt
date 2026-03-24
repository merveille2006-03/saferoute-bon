package com.saferoute.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.saferoute.domain.model.LocationData
import com.saferoute.domain.model.SOSAlert

@Entity(
    tableName = "sos_alerts",
    indices = [Index(value = ["userId"]), Index(value = ["timestamp"])]
)
data class SOSAlertEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val timestamp: Long,
    val location: LocationData?,
    val triggerType: String,
    val message: String,
    val recipients: List<String>,
    val status: String,
    val sentAt: Long?,
    val deliveredCount: Int,
    val failedCount: Int
) {
    fun toDomainModel(): SOSAlert = SOSAlert(
        id = id,
        userId = userId,
        timestamp = timestamp,
        location = location,
        triggerType = triggerType,
        message = message,
        recipients = recipients,
        status = status,
        sentAt = sentAt,
        deliveredCount = deliveredCount,
        failedCount = failedCount
    )

    companion object {
        fun fromDomainModel(alert: SOSAlert): SOSAlertEntity = SOSAlertEntity(
            id = alert.id,
            userId = alert.userId,
            timestamp = alert.timestamp,
            location = alert.location,
            triggerType = alert.triggerType,
            message = alert.message,
            recipients = alert.recipients,
            status = alert.status,
            sentAt = alert.sentAt,
            deliveredCount = alert.deliveredCount,
            failedCount = alert.failedCount
        )
    }
}