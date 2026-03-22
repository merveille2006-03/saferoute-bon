package com.saferoute
import com.saferoute.BuildConfig
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SafeRouteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Initialize Timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        // Create notification channels
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channels = listOf(
                NotificationChannel(
                    CHANNEL_FALL_DETECTION,
                    "Détection de chute",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Alertes de détection de chute"
                    setSound(null, null)
                },
                NotificationChannel(
                    CHANNEL_LOCATION_TRACKING,
                    "Suivi de localisation",
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = "Suivi GPS en arrière-plan"
                    setSound(null, null)
                },
                NotificationChannel(
                    CHANNEL_SOS_ALERT,
                    "Alertes SOS",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Alertes d'urgence SOS"
                },
                NotificationChannel(
                    CHANNEL_SAFE_ZONE,
                    "Zones sécurisées",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Alertes de sortie de zone sécurisée"
                },
                NotificationChannel(
                    CHANNEL_BLUETOOTH,
                    "Bluetooth",
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = "Communication Bluetooth"
                    setSound(null, null)
                }
            )

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannels(channels)
        }
    }

    companion object {
        const val CHANNEL_FALL_DETECTION = "fall_detection_channel"
        const val CHANNEL_LOCATION_TRACKING = "location_tracking_channel"
        const val CHANNEL_SOS_ALERT = "sos_alert_channel"
        const val CHANNEL_SAFE_ZONE = "safe_zone_channel"
        const val CHANNEL_BLUETOOTH = "bluetooth_channel"
    }
}
