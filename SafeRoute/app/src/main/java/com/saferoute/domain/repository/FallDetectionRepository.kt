package com.saferoute.domain.repository

import com.saferoute.domain.model.FallDetectionState
import com.saferoute.domain.model.FallEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface FallDetectionRepository {
    val fallDetectionState: StateFlow<FallDetectionState>
    val currentFallEvent: StateFlow<FallEvent?>

    suspend fun startMonitoring()
    suspend fun stopMonitoring()
    suspend fun recordFallEvent(event: FallEvent): Long
    suspend fun confirmFall(eventId: Long, isRealFall: Boolean)
    suspend fun cancelCurrentAlert()
    suspend fun getFallHistory(userId: String, limit: Int = 50): List<FallEvent>
    fun getFallHistoryFlow(userId: String): Flow<List<FallEvent>>
    suspend fun updateFallEvent(event: FallEvent)
    fun isAccelerometerAvailable(): Boolean
    suspend fun setSensitivity(level: Int)
}