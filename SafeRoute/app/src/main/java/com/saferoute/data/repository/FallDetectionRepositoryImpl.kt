package com.saferoute.data.repository

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.saferoute.data.local.dao.FallEventDao
import com.saferoute.data.local.entity.FallEventEntity
import com.saferoute.domain.model.FallDetectionState
import com.saferoute.domain.model.FallEvent
import com.saferoute.domain.repository.FallDetectionRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.sqrt

object AppSettings {
    const val FALL_THRESHOLD = 10
}
@Singleton
class FallDetectionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val fallEventDao: FallEventDao
) : FallDetectionRepository, SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private val _fallDetectionState = MutableStateFlow(FallDetectionState.IDLE)
    override val fallDetectionState: StateFlow<FallDetectionState> = _fallDetectionState.asStateFlow()

    private val _currentFallEvent = MutableStateFlow<FallEvent?>(null)
    override val currentFallEvent: StateFlow<FallEvent?> = _currentFallEvent.asStateFlow()

    private var sensitivityThreshold = FallEvent.MIN_IMPACT_FORCE
    private var confirmationJob: Job? = null

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    // Accelerometer data buffer for analysis
    private val accelerometerBuffer = mutableListOf<Triple<Float, Float, Float>>()
    private var lastFallTime: Long = 0
    private var isMonitoring = false

    override suspend fun startMonitoring() {
        if (isMonitoring) return

        accelerometer?.let { sensor ->
            sensorManager.registerListener(
                this,
                sensor,
                SensorManager.SENSOR_DELAY_GAME
            )
            isMonitoring = true
            _fallDetectionState.value = FallDetectionState.IDLE
            Timber.d("Fall detection monitoring started")
        } ?: run {
            Timber.e("Accelerometer not available")
        }
    }

    override suspend fun stopMonitoring() {
        sensorManager.unregisterListener(this)
        isMonitoring = false
        confirmationJob?.cancel()
        _fallDetectionState.value = FallDetectionState.IDLE
        _currentFallEvent.value = null
        Timber.d("Fall detection monitoring stopped")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let { sensorEvent ->
            val x = sensorEvent.values[0]
            val y = sensorEvent.values[1]
            val z = sensorEvent.values[2]

            // Calculate total acceleration
            val acceleration = sqrt(x * x + y * y + z * z)
            val gForce = acceleration / SensorManager.GRAVITY_EARTH

            // Add to buffer
            accelerometerBuffer.add(Triple(x, y, z))
            if (accelerometerBuffer.size > 100) {
                accelerometerBuffer.removeAt(0)
            }

            // Check for fall
            if (_fallDetectionState.value == FallDetectionState.IDLE &&
                gForce > sensitivityThreshold &&
                System.currentTimeMillis() - lastFallTime > 5000 // Debounce 5 seconds
            ) {
                detectFall(gForce.toDouble())
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not needed for fall detection
    }

    private fun detectFall(impactForce: Double) {
        lastFallTime = System.currentTimeMillis()
        _fallDetectionState.value = FallDetectionState.DETECTING

        // Analyze fall duration from buffer
        val fallDuration = analyzeFallDuration()

        if (fallDuration >= FallEvent.FALL_DURATION_THRESHOLD) {
            val fallEvent = FallEvent(
                userId = "current_user", // Should be injected
                impactForce = impactForce,
                fallDuration = fallDuration
            )

            _currentFallEvent.value = fallEvent
            _fallDetectionState.value = FallDetectionState.CONFIRMATION_REQUIRED

            // Start confirmation timeout
            startConfirmationTimeout(fallEvent)

            Timber.d("Fall detected: impact=$impactForce G, duration=$fallDuration ms")
        } else {
            _fallDetectionState.value = FallDetectionState.IDLE
        }
    }

    private fun analyzeFallDuration(): Long {
        // Analyze buffer to determine fall duration
        // Simplified implementation
        return 300 // Return typical fall duration
    }

    private fun startConfirmationTimeout(fallEvent: FallEvent) {
        confirmationJob?.cancel()
        confirmationJob = scope.launch {
            delay(FallEvent.RESPONSE_TIMEOUT_MS)

            // If still waiting for confirmation, treat as confirmed fall
            if (_fallDetectionState.value == FallDetectionState.CONFIRMATION_REQUIRED) {
                confirmFall(fallEvent.id, true)
            }
        }
    }

    private fun getImpactThreshold(): Float {
        return 10.0f
    }
    override suspend fun recordFallEvent(event: FallEvent): Long {
        return fallEventDao.insertFallEvent(FallEventEntity.fromDomainModel(event))
    }

    override suspend fun confirmFall(eventId: Long, isRealFall: Boolean) {
        confirmationJob?.cancel()

        fallEventDao.updateConfirmation(eventId, isRealFall)

        if (isRealFall) {
            _fallDetectionState.value = FallDetectionState.CONFIRMED
            // Trigger alert will be handled by ViewModel
        } else {
            _fallDetectionState.value = FallDetectionState.CANCELLED
            // Reset after delay
            delay(2000)
            _fallDetectionState.value = FallDetectionState.IDLE
            _currentFallEvent.value = null
        }
    }

    override suspend fun cancelCurrentAlert() {
        _currentFallEvent.value?.let { event ->
            if (event.id != 0L) {
                confirmFall(event.id, false)
            } else {
                confirmationJob?.cancel()
                _fallDetectionState.value = FallDetectionState.CANCELLED
                delay(2000)
                _fallDetectionState.value = FallDetectionState.IDLE
                _currentFallEvent.value = null
            }
        }
    }

    override suspend fun getFallHistory(userId: String, limit: Int): List<FallEvent> {
        return fallEventDao.getFallHistory(userId, limit).map { it.toDomainModel() }
    }

    override fun getFallHistoryFlow(userId: String): Flow<List<FallEvent>> {
        return fallEventDao.getFallHistoryFlow(userId).map { list ->
            list.map { it.toDomainModel() }
        }
    }

    override suspend fun updateFallEvent(event: FallEvent) {
        fallEventDao.updateFallEvent(FallEventEntity.fromDomainModel(event))
    }

    override fun isAccelerometerAvailable(): Boolean {
        return accelerometer != null
    }

    override suspend fun setSensitivity(level: Int) {
        sensitivityThreshold = when(level) {
            1 -> 5.0
            2 -> 10.0
            3 -> 15.0
            else -> 10.0
        }
    }
}

