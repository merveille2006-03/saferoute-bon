package com.saferoute.data.local.dao

import androidx.room.*
import com.saferoute.data.local.entity.SOSAlertEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SOSAlertDao {
    @Query("SELECT * FROM sos_alerts WHERE userId = :userId ORDER BY timestamp DESC")
    suspend fun getAllAlerts(userId: String): List<SOSAlertEntity>

    @Query("SELECT * FROM sos_alerts WHERE userId = :userId ORDER BY timestamp DESC")
    fun getAllAlertsFlow(userId: String): Flow<List<SOSAlertEntity>>

    @Query("SELECT * FROM sos_alerts WHERE id = :alertId")
    suspend fun getAlertById(alertId: Long): SOSAlertEntity?

    @Query("SELECT * FROM sos_alerts WHERE userId = :userId AND status = :status ORDER BY timestamp DESC")
    suspend fun getAlertsByStatus(userId: String, status: String): List<SOSAlertEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlert(alert: SOSAlertEntity): Long

    @Update
    suspend fun updateAlert(alert: SOSAlertEntity)

    @Query("UPDATE sos_alerts SET status = :status WHERE id = :alertId")
    suspend fun updateStatus(alertId: Long, status: String)

    @Query("UPDATE sos_alerts SET deliveredCount = :count WHERE id = :alertId")
    suspend fun updateDeliveredCount(alertId: Long, count: Int)

    @Delete
    suspend fun deleteAlert(alert: SOSAlertEntity)

    @Query("DELETE FROM sos_alerts WHERE userId = :userId")
    suspend fun deleteAllAlerts(userId: String)
}