package com.saferoute.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.saferoute.R
import com.saferoute.SafeRouteApplication
import com.saferoute.domain.model.LocationData
import com.saferoute.domain.model.SOSAlert
import com.saferoute.domain.repository.AlertRepository
import com.saferoute.domain.repository.LocationRepository
import com.saferoute.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SOSAlertService : Service() {

    @Inject
    lateinit var alertRepository: AlertRepository

    @Inject
    lateinit var locationRepository: LocationRepository

    private val binder = LocalBinder()
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var countDownTimer: CountDownTimer? = null
    private var countdownSeconds: Int = 5
    private var userId: String = "default_user"

    inner class LocalBinder : Binder() {
        fun getService(): SOSAlertService = this@SOSAlertService
    }

    override fun onCreate() {
        super.onCreate()
        Timber.d("SOSAlertService created")
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START_COUNTDOWN -> {
                countdownSeconds = intent.getIntExtra(EXTRA_COUNTDOWN, 5)
                startCountdown()
            }
            ACTION_CANCEL_COUNTDOWN -> cancelCountdown()
            ACTION_SEND_IMMEDIATE -> sendImmediateAlert()
        }
        return START_NOT_STICKY
    }

    private fun startCountdown() {
        startForeground(NOTIFICATION_ID, createCountdownNotification(countdownSeconds))

        countDownTimer = object : CountDownTimer(countdownSeconds * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                updateNotification(secondsRemaining)
            }

            override fun onFinish() {
                sendAlert()
            }
        }.start()
    }

    private fun cancelCountdown() {
        countDownTimer?.cancel()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun sendImmediateAlert() {
        countDownTimer?.cancel()
        sendAlert()
    }

    private fun sendAlert() {
        serviceScope.launch {
            val location = locationRepository.getCurrentLocation()

            val result = alertRepository.sendSOSAlert(
                userId = userId,
                location = location,
                triggerType = SOSAlert.TRIGGER_MANUAL
            )

            if (result.isSuccess) {
                showAlertSentNotification()
            } else {
                showAlertFailedNotification()
            }

            delay(5000)
            stopForeground(STOP_FOREGROUND_REMOVE)
            stopSelf()
        }
    }

    private fun createCountdownNotification(seconds: Int): Notification {
        val cancelIntent = PendingIntent.getService(
            this,
            0,
            Intent(this, SOSAlertService::class.java).setAction(ACTION_CANCEL_COUNTDOWN),
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, SafeRouteApplication.CHANNEL_SOS_ALERT)
            .setContentTitle("Alerte SOS dans $seconds secondes")
            .setContentText("Appuyez pour annuler")
            .setSmallIcon(R.drawable.ic_sos)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setOngoing(true)
            .setContentIntent(cancelIntent)
            .addAction(R.drawable.ic_cancel, "Annuler", cancelIntent)
            .build()
    }

    private fun updateNotification(seconds: Int) {
        val notification = createCountdownNotification(seconds)
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun showAlertSentNotification() {
        val notification = NotificationCompat.Builder(this, SafeRouteApplication.CHANNEL_SOS_ALERT)
            .setContentTitle("Alerte SOS envoyée")
            .setContentText("Vos contacts d'urgence ont été notifiés.")
            .setSmallIcon(R.drawable.ic_check)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    private fun showAlertFailedNotification() {
        val notification = NotificationCompat.Builder(this, SafeRouteApplication.CHANNEL_SOS_ALERT)
            .setContentTitle("Échec de l'alerte")
            .setContentText("Impossible d'envoyer l'alerte SOS.")
            .setSmallIcon(R.drawable.ic_error)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
        serviceScope.cancel()
        Timber.d("SOSAlertService destroyed")
    }

    companion object {
        const val NOTIFICATION_ID = 1003
        const val ACTION_START_COUNTDOWN = "action_start_countdown"
        const val ACTION_CANCEL_COUNTDOWN = "action_cancel_countdown"
        const val ACTION_SEND_IMMEDIATE = "action_send_immediate"
        const val EXTRA_COUNTDOWN = "extra_countdown"
    }
}