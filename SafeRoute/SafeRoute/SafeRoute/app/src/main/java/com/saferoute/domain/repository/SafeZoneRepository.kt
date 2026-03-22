package com.saferoute.domain.repository

import com.saferoute.domain.model.LocationData
import com.saferoute.domain.model.SafeZone
import kotlinx.coroutines.flow.Flow

interface SafeZoneRepository {
    suspend fun getAllSafeZones(userId: String): List<SafeZone>
    fun getAllSafeZonesFlow(userId: String): Flow<List<SafeZone>>
    suspend fun getSafeZoneById(zoneId: Long): SafeZone?
    suspend fun addSafeZone(safeZone: SafeZone): Long
    suspend fun updateSafeZone(safeZone: SafeZone)
    suspend fun deleteSafeZone(safeZone: SafeZone)
    suspend fun deleteSafeZoneById(zoneId: Long)
    suspend fun getActiveSafeZones(userId: String): List<SafeZone>
    suspend fun isLocationInSafeZone(location: LocationData, userId: String): SafeZone?
    suspend fun checkSafeZoneExit(location: LocationData, userId: String): SafeZone?
}