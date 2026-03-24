package com.saferoute.data.local.dao

import androidx.room.*
import com.saferoute.data.local.entity.SafeZoneEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SafeZoneDao {
    @Query("SELECT * FROM safe_zones WHERE userId = :userId ORDER BY createdAt DESC")
    suspend fun getAllSafeZones(userId: String): List<SafeZoneEntity>

    @Query("SELECT * FROM safe_zones WHERE userId = :userId ORDER BY createdAt DESC")
    fun getAllSafeZonesFlow(userId: String): Flow<List<SafeZoneEntity>>

    @Query("SELECT * FROM safe_zones WHERE id = :zoneId")
    suspend fun getSafeZoneById(zoneId: Long): SafeZoneEntity?

    @Query("SELECT * FROM safe_zones WHERE userId = :userId AND isActive = 1")
    suspend fun getActiveSafeZones(userId: String): List<SafeZoneEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSafeZone(zone: SafeZoneEntity): Long

    @Update
    suspend fun updateSafeZone(zone: SafeZoneEntity)

    @Delete
    suspend fun deleteSafeZone(zone: SafeZoneEntity)

    @Query("DELETE FROM safe_zones WHERE id = :zoneId")
    suspend fun deleteSafeZoneById(zoneId: Long)

    @Query("DELETE FROM safe_zones WHERE userId = :userId")
    suspend fun deleteAllSafeZones(userId: String)
}