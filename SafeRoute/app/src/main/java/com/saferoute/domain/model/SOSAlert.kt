package com.saferoute.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "sos_alerts")
data class SOSAlert(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val timestamp: Long = System.currentTimeMillis(),
    val location: LocationData? = null,
    val triggerType: String, // manual, fall_detection, safe_zone_exit
    val message: String,
    val recipients: List<String>, // phone numbers
    val status: String = STATUS_PENDING,
    val sentAt: Long? = null,
    val deliveredCount: Int = 0,
    val failedCount: Int = 0
) {
    companion object {
        const val TRIGGER_MANUAL = "manual"
        const val TRIGGER_FALL_DETECTION = "fall_detection"
        const val TRIGGER_SAFE_ZONE_EXIT = "safe_zone_exit"
        const val TRIGGER_BLUETOOTH = "bluetooth"
        
        const val STATUS_PENDING = "pending"
        const val STATUS_SENT = "sent"
        const val STATUS_DELIVERED = "delivered"
        const val STATUS_FAILED = "failed"
        
        fun createDefaultMessage(userName: String, location: LocationData?): String {
            return buildString {
                append("🚨 ALERTE SAFEROUTE - $userName a besoin d'aide!")
                location?.let {
                    append("\n📍 Position: https://maps.google.com/?q=${it.latitude},${it.longitude}")
                    append("\n🕒 ${java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss", java.util.Locale.FRANCE).format(java.util.Date())}")
                }
                append("\n⚠️ Cette alerte a été envoyée automatiquement.")
            }
        }
    }
}
