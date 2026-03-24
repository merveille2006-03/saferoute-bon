package com.saferoute.domain.repository

import com.saferoute.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun getSettings(userId: String): AppSettings
    fun getSettingsFlow(userId: String): Flow<AppSettings>
    suspend fun saveSettings(settings: AppSettings)
    suspend fun updateSettings(userId: String, update: (AppSettings) -> AppSettings)
    suspend fun resetSettings(userId: String)
}