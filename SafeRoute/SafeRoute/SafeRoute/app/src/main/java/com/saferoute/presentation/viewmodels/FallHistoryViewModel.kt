package com.saferoute.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saferoute.domain.model.FallEvent
import com.saferoute.domain.repository.FallDetectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FallHistoryViewModel @Inject constructor(
    private val fallDetectionRepository: FallDetectionRepository
) : ViewModel() {

    private val userId = "default_user"

    private val _fallEvents = MutableStateFlow<List<FallEvent>>(emptyList())
    val fallEvents: StateFlow<List<FallEvent>> = _fallEvents.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadFallHistory()
    }

    private fun loadFallHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                fallDetectionRepository.getFallHistoryFlow(userId).collect { events ->
                    _fallEvents.value = events
                }
            } catch (e: Exception) {
                Timber.e(e, "Failed to load fall history")
                _errorMessage.value = "Impossible de charger l'historique"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun confirmFall(eventId: Long, isRealFall: Boolean) {
        viewModelScope.launch {
            try {
                fallDetectionRepository.confirmFall(eventId, isRealFall)
            } catch (e: Exception) {
                Timber.e(e, "Failed to confirm fall")
                _errorMessage.value = "Impossible de confirmer la chute"
            }
        }
    }

    fun refreshHistory() {
        loadFallHistory()
    }

    fun clearError() {
        _errorMessage.value = null
    }
}