package com.saferoute.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationData(
    val latitude: Double,
    val longitude: Double,
    val accuracy: Float,
    val altitude: Double,
    val speed: Float,
    val bearing: Float,
    val timestamp: Long = System.currentTimeMillis(),
    val provider: String = "gps"
) {
    fun toLatLng(): Pair<Double, Double> = Pair(latitude, longitude)
    
    fun isValid(): Boolean {
        return latitude != 0.0 && longitude != 0.0 &&
               latitude in -90.0..90.0 && longitude in -180.0..180.0
    }
}

@Serializable
data class LocationHistory(
    val id: Long = 0,
    val userId: String,
    val location: LocationData,
    val isInSafeZone: Boolean = false,
    val safeZoneId: Long? = null
)
