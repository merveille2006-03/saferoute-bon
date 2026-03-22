package com.saferoute.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.saferoute.R
import com.saferoute.SafeRouteApplication
import com.saferoute.domain.model.FallDetectionState
import com.saferoute.domain.model.FallEvent
import com.saferoute.domain.model.LocationData
import com.saferoute.domain.repository.AlertRepository
import com.saferoute.domain.repository.FallDetectionRepository
import com.saferoute.domain.repository.LocationRepository
import com.saferoute.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FallDetectionService : Service() {

    @Inject
    lateinit var fallDetectionRepository: FallDetectionRepository

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var alertRepository: AlertRepository

    private val binder = LocalBinder()
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    inner class LocalBinder : Binder() {
        fun getService(): FallDetectionService = this@FallDetectionService
    }

    override fun onCreate() {
        super.onCreate()
        Timber.d("FallDetectionService created")
        startForeground(NOTIFICATION_ID, createNotification())
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startFallDetection()
            ACTION_STOP -> stopFallDetection()
            ACTION_CONFIRM_FALL -> confirmFall(true)
            ACTION_CANCEL_FALL -> confirmFall(false)
        }
        return START_STICKY
    }

    private fun startFallDetection() {
        serviceScope.launch {
            fallDetectionRepository.startMonitoring()

            fallDetectionRepository.fallDetectionState.collectLatest { state ->
                when (state) {
                    FallDetectionState.CONFIRMATION_REQUIRED -> {
                        showFallConfirmationNotification()
                    }
                    FallDetectionState.CONFIRMED -> {
                        sendFallAlert()
                    }
                    else -> { /* Do nothing */ }
                }
            }
        }
    }

    private fun stopFallDetection() {
        serviceScope.launch {
            fallDetectionRepository.stopMonitoring()
        }
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun confirmFall(isRealFall: Boolean) {
        serviceScope.launch {
            fallDetectionRepository.currentFallEvent.value?.let { event ->
                fallDetectionRepository.confirmFall(event.id, isRealFall)
            }
        }
    }

    private suspend fun sendFallAlert() {
        val location = locationRepository.getCurrentLocation()
        val event = fallDetectionRepository.currentFallEvent.value

        event?.let { fallEvent ->
            val updatedEvent = fallEvent.copy(
                location = location,
                alertSent = true,
                alertSentAt = System.currentTimeMillis()
            )
            fallDetectionRepository.updateFallEvent(updatedEvent)

            alertRepository.sendSOSAlert(
                userId = fallEvent.userId,
                location = location,
                triggerType = FallEvent::class.simpleName ?: "fall_detection"
            )

            showAlertSentNotification()
        }
    }

    private fun createNotification(): Notification {
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, SafeRouteApplication.CHANNEL_FALL_DETECTION)
            .setContentTitle("Détection de chute active")
            .setContentText("Surveillance en arrière-plan...")
            .setSmallIcon(R.drawable.ic_fall_detection)
            .setOngoing(true)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun showFallConfirmationNotification() {
        val confirmIntent = PendingIntent.getService(
            this,
            0,
            Intent(this, FallDetectionService::class.java).setAction(ACTION_CONFIRM_FALL),
            PendingIntent.FLAG_IMMUTABLE
        )

        val cancelIntent = PendingIntent.getService(
            this,
            1,
            Intent(this, FallDetectionService::class.java).setAction(ACTION_CANCEL_FALL),
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, SafeRouteApplication.CHANNEL_FALL_DETECTION)
            .setContentTitle("Chute détectée!")
            .setContentText("Êtes-vous OK? Répondez dans 30 secondes.")
            .setSmallIcon(R.drawable.ic_warning)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setVibrate(longArrayOf(0, 500, 200, 500))
            .addAction(R.drawable.ic_check, "Je vais bien", cancelIntent)
            .addAction(R.drawable.ic_alert, "Alerte", confirmIntent)
            .setTimeoutAfter(FallEvent.RESPONSE_TIMEOUT_MS)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    private fun showAlertSentNotification() {
        val notification = NotificationCompat.Builder(this, SafeRouteApplication.CHANNEL_SOS_ALERT)
            .setContentTitle("Alerte envoyée")
            .setContentText("Vos contacts d'urgence ont été alertés.")
            .setSmallIcon(R.drawable.ic_alert)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        Timber.d("FallDetectionService destroyed")
    }

    companion object {
        const val NOTIFICATION_ID = 1001
        const val ACTION_START = "action_start"
        const val ACTION_STOP = "action_stop"
        const val ACTION_CONFIRM_FALL = "action_confirm_fall"
        const val ACTION_CANCEL_FALL = "action_cancel_fall"
    }
}