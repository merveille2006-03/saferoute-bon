package com.saferoute.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saferoute.domain.model.SOSAlert
import com.saferoute.domain.repository.AlertRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlertHistoryViewModel @Inject constructor(
    private val alertRepository: AlertRepository
) : ViewModel() {

    private val userId = "default_user"

    private val _alerts = MutableStateFlow<List<SOSAlert>>(emptyList())
    val alerts: StateFlow<List<SOSAlert>> = _alerts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _stats = MutableStateFlow(AlertStats())
    val stats: StateFlow<AlertStats> = _stats.asStateFlow()

    init {
        loadAlertHistory()
    }

    private fun loadAlertHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                alertRepository.getAlertHistoryFlow(userId).collect { alertList ->
                    _alerts.value = alertList
                    calculateStats(alertList)
                }
            } catch (e: Exception) {
                Timber.e(e, "Failed to load alert history")
                _errorMessage.value = "Impossible de charger l'historique des alertes"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun calculateStats(alerts: List<SOSAlert>) {
        val totalAlerts = alerts.size
        val manualAlerts = alerts.count { it.triggerType == SOSAlert.TRIGGER_MANUAL }
        val fallAlerts = alerts.count { it.triggerType == SOSAlert.TRIGGER_FALL_DETECTION }
        val safeZoneAlerts = alerts.count { it.triggerType == SOSAlert.TRIGGER_SAFE_ZONE_EXIT }
        val successfulAlerts = alerts.count { it.status == SOSAlert.STATUS_DELIVERED }

        _stats.value = AlertStats(
            totalAlerts = totalAlerts,
            manualAlerts = manualAlerts,
            fallAlerts = fallAlerts,
            safeZoneAlerts = safeZoneAlerts,
            successfulAlerts = successfulAlerts,
            successRate = if (totalAlerts > 0) (successfulAlerts * 100 / totalAlerts) else 0
        )
    }

    fun refreshHistory() {
        loadAlertHistory()
    }

    fun clearError() {
        _errorMessage.value = null
    }

    data class AlertStats(
        val totalAlerts: Int = 0,
        val manualAlerts: Int = 0,
        val fallAlerts: Int = 0,
        val safeZoneAlerts: Int = 0,
        val successfulAlerts: Int = 0,
        val successRate: Int = 0
    )
}