package com.saferoute.data.repository

import com.saferoute.data.local.dao.SafeZoneDao
import com.saferoute.data.local.entity.SafeZoneEntity
import com.saferoute.domain.model.LocationData
import com.saferoute.domain.model.SafeZone
import com.saferoute.domain.repository.SafeZoneRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.*

@Singleton
class SafeZoneRepositoryImpl @Inject constructor(
    private val safeZoneDao: SafeZoneDao
) : SafeZoneRepository {

    override suspend fun getAllSafeZones(userId: String): List<SafeZone> {
        return safeZoneDao.getAllSafeZones(userId).map { it.toDomainModel() }
    }

    override fun getAllSafeZonesFlow(userId: String): Flow<List<SafeZone>> {
        return safeZoneDao.getAllSafeZonesFlow(userId).map { list ->
            list.map { it.toDomainModel() }
        }
    }

    override suspend fun getSafeZoneById(zoneId: Long): SafeZone? {
        return safeZoneDao.getSafeZoneById(zoneId)?.toDomainModel()
    }

    override suspend fun addSafeZone(safeZone: SafeZone): Long {
        return safeZoneDao.insertSafeZone(SafeZoneEntity.fromDomainModel(safeZone))
    }

    override suspend fun updateSafeZone(safeZone: SafeZone) {
        safeZoneDao.updateSafeZone(SafeZoneEntity.fromDomainModel(safeZone))
    }

    override suspend fun deleteSafeZone(safeZone: SafeZone) {
        safeZoneDao.deleteSafeZone(SafeZoneEntity.fromDomainModel(safeZone))
    }

    override suspend fun deleteSafeZoneById(zoneId: Long) {
        safeZoneDao.deleteSafeZoneById(zoneId)
    }

    override suspend fun getActiveSafeZones(userId: String): List<SafeZone> {
        return safeZoneDao.getActiveSafeZones(userId).map { it.toDomainModel() }
    }

    override suspend fun isLocationInSafeZone(location: LocationData, userId: String): SafeZone? {
        val zones = getActiveSafeZones(userId)
        return zones.find { zone ->
            calculateDistance(location, zone) <= zone.radius
        }
    }

    override suspend fun checkSafeZoneExit(location: LocationData, userId: String): SafeZone? {
        val zones = getActiveSafeZones(userId)
        return zones.find { zone ->
            zone.alertOnExit && calculateDistance(location, zone) > zone.radius
        }
    }

    private fun calculateDistance(location: LocationData, zone: SafeZone): Double {
        val earthRadius = 6371000.0 // meters

        val lat1 = Math.toRadians(location.latitude)
        val lat2 = Math.toRadians(zone.latitude)
        val deltaLat = Math.toRadians(zone.latitude - location.latitude)
        val deltaLon = Math.toRadians(zone.longitude - location.longitude)

        val a = sin(deltaLat / 2).pow(2) +
                cos(lat1) * cos(lat2) * sin(deltaLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return earthRadius * c
    }
}