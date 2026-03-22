package com.saferoute.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.saferoute.domain.repository.SettingsRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Timber.d("Boot completed - starting SafeRoute services")

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val settings = settingsRepository.getSettings("default_user")

                    // Start fall detection if enabled
                    if (settings.isFallDetectionEnabled) {
                        startFallDetectionService(context)
                    }

                    // Start location tracking if enabled
                    if (settings.isLocationTrackingEnabled) {
                        startLocationTrackingService(context)
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Failed to start services after boot")
                }
            }
        }
    }

    private fun startFallDetectionService(context: Context) {
        val serviceIntent = Intent(context, FallDetectionService::class.java).apply {
            action = FallDetectionService.ACTION_START
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }

    private fun startLocationTrackingService(context: Context) {
        val serviceIntent = Intent(context, LocationTrackingService::class.java).apply {
            action = LocationTrackingService.ACTION_START
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }
}