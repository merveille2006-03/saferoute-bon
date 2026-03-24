package com.saferoute.domain.repository

import com.saferoute.domain.model.LocationData
import com.saferoute.domain.model.SOSAlert
import kotlinx.coroutines.flow.Flow

interface AlertRepository {
    suspend fun sendSOSAlert(
        userId: String,
        location: LocationData?,
        triggerType: String,
        customMessage: String? = null
    ): Result<SOSAlert>
    
    suspend fun sendSMSAlert(phoneNumbers: List<String>, message: String): Result<Int>
    suspend fun sendEmailAlert(emails: List<String>, subject: String, body: String): Result<Int>
    
    suspend fun getAlertHistory(userId: String): List<SOSAlert>
    fun getAlertHistoryFlow(userId: String): Flow<List<SOSAlert>>
    suspend fun getAlertById(alertId: Long): SOSAlert?
    suspend fun updateAlertStatus(alertId: Long, status: String)
    
    suspend fun testAlertSystem(): Boolean
}