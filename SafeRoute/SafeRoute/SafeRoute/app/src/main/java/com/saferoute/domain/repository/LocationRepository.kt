package com.saferoute.domain.repository

import com.saferoute.domain.model.LocationData
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getCurrentLocation(): LocationData?
    fun getLocationUpdates(intervalMs: Long): Flow<LocationData>
    suspend fun saveLocationHistory(location: LocationData, userId: String)
    suspend fun getLocationHistory(userId: String, limit: Int = 100): List<LocationData>
    fun isLocationPermissionGranted(): Boolean
    fun isBackgroundLocationPermissionGranted(): Boolean
    suspend fun getLastKnownLocation(): LocationData?
}