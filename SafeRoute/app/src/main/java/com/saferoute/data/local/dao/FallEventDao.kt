package com.saferoute.data.local.dao

import androidx.room.*
import com.saferoute.data.local.entity.FallEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FallEventDao {
    @Query("SELECT * FROM fall_events WHERE userId = :userId ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getFallHistory(userId: String, limit: Int): List<FallEventEntity>

    @Query("SELECT * FROM fall_events WHERE userId = :userId ORDER BY timestamp DESC")
    fun getFallHistoryFlow(userId: String): Flow<List<FallEventEntity>>

    @Query("SELECT * FROM fall_events WHERE id = :eventId")
    suspend fun getFallEventById(eventId: Long): FallEventEntity?

    @Query("SELECT * FROM fall_events WHERE userId = :userId AND alertSent = 0 AND isConfirmed IS NULL ORDER BY timestamp DESC LIMIT 1")
    suspend fun getPendingFallEvent(userId: String): FallEventEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFallEvent(event: FallEventEntity): Long

    @Update
    suspend fun updateFallEvent(event: FallEventEntity)

    @Query("UPDATE fall_events SET isConfirmed = :isConfirmed WHERE id = :eventId")
    suspend fun updateConfirmation(eventId: Long, isConfirmed: Boolean)

    @Query("UPDATE fall_events SET alertSent = 1, alertSentAt = :timestamp WHERE id = :eventId")
    suspend fun markAlertSent(eventId: Long, timestamp: Long = System.currentTimeMillis())

    @Delete
    suspend fun deleteFallEvent(event: FallEventEntity)

    @Query("DELETE FROM fall_events WHERE userId = :userId")
    suspend fun deleteAllFallEvents(userId: String)
}