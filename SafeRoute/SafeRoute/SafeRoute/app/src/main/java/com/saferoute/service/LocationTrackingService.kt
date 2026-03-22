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
import com.saferoute.domain.model.LocationData
import com.saferoute.domain.repository.AlertRepository
import com.saferoute.domain.repository.LocationRepository
import com.saferoute.domain.repository.SafeZoneRepository
import com.saferoute.domain.repository.SettingsRepository
import com.saferoute.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LocationTrackingService : Service() {

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var safeZoneRepository: SafeZoneRepository

    @Inject
    lateinit var alertRepository: AlertRepository

    @Inject
    lateinit var settingsRepository: SettingsRepository

    private val binder = LocalBinder()
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var lastSafeZoneId: Long? = null
    private var userId: String = "default_user"

    inner class LocalBinder : Binder() {
        fun getService(): LocationTrackingService = this@LocationTrackingService
    }

    override fun onCreate() {
        super.onCreate()
        Timber.d("LocationTrackingService created")
        startForeground(NOTIFICATION_ID, createNotification())
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startTracking()
            ACTION_STOP -> stopTracking()
        }
        return START_STICKY
    }

    private fun startTracking() {
        serviceScope.launch {
            val settings = settingsRepository.getSettings(userId)
            val interval = settings.trackingIntervalMs

            locationRepository.getLocationUpdates(interval).collectLatest { location ->
                processLocation(location)
            }
        }
    }

    private fun stopTracking() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private suspend fun processLocation(location: LocationData) {
        if (!location.isValid()) return

        // Check safe zone status
        val currentZone = safeZoneRepository.isLocationInSafeZone(location, userId)

        if (currentZone != null) {
            // User is in a safe zone
            lastSafeZoneId = currentZone.id
            Timber.d("In safe zone: ${currentZone.name}")
        } else {
            // User is outside safe zones
            if (lastSafeZoneId != null) {
                // User just exited a safe zone
                val exitedZone = safeZoneRepository.getSafeZoneById(lastSafeZoneId!!)
                if (exitedZone?.alertOnExit == true) {
                    sendSafeZoneExitAlert(location, exitedZone.name)
                }
                lastSafeZoneId = null
            }
        }
    }

    private suspend fun sendSafeZoneExitAlert(location: LocationData, zoneName: String) {
        val message = "ALERTE: Sortie anormale de la zone '$zoneName'. " +
                "Position: https://maps.google.com/?q=${location.latitude},${location.longitude}"

        alertRepository.sendSOSAlert(
            userId = userId,
            location = location,
            triggerType = "safe_zone_exit",
            customMessage = message
        )

        showSafeZoneExitNotification(zoneName)
    }

    private fun createNotification(): Notification {
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, SafeRouteApplication.CHANNEL_LOCATION_TRACKING)
            .setContentTitle("Suivi de localisation actif")
            .setContentText("SafeRoute surveille votre position")
            .setSmallIcon(R.drawable.ic_location)
            .setOngoing(true)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun showSafeZoneExitNotification(zoneName: String) {
        val notification = NotificationCompat.Builder(this, SafeRouteApplication.CHANNEL_SAFE_ZONE)
            .setContentTitle("Sortie de zone détectée")
            .setContentText("Vous avez quitté $zoneName")
            .setSmallIcon(R.drawable.ic_safe_zone)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0, 300, 100, 300))
            .build()

        // Show as separate notification
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
        notificationManager.notify(NOTIFICATION_ID + 1, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        Timber.d("LocationTrackingService destroyed")
    }

    companion object {
        const val NOTIFICATION_ID = 1002
        const val ACTION_START = "action_start"
        const val ACTION_STOP = "action_stop"
    }
}