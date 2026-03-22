package com.saferoute.data.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.saferoute.domain.model.LocationData
import com.saferoute.domain.repository.LocationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class LocationRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationRepository {

    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    override suspend fun getCurrentLocation(): LocationData? {
        if (!isLocationPermissionGranted()) return null

        return suspendCancellableCoroutine { continuation ->
            try {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        continuation.resume(location?.toLocationData())
                    }
                    .addOnFailureListener {
                        continuation.resume(null)
                    }
            } catch (e: SecurityException) {
                continuation.resume(null)
            }
        }
    }

    override fun getLocationUpdates(intervalMs: Long): Flow<LocationData> = callbackFlow {
        if (!isLocationPermissionGranted()) {
            close(SecurityException("Location permission not granted"))
            return@callbackFlow
        }

        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            intervalMs
        ).apply {
            setMinUpdateIntervalMillis(intervalMs / 2)
            setWaitForAccurateLocation(true)
        }.build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { location ->
                    trySend(location.toLocationData())
                }
            }
        }

        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            close(e)
        }

        awaitClose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    override suspend fun saveLocationHistory(location: LocationData, userId: String) {
        // Implementation would save to database
    }

    override suspend fun getLocationHistory(userId: String, limit: Int): List<LocationData> {
        // Implementation would retrieve from database
        return emptyList()
    }

    override fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun isBackgroundLocationPermissionGranted(): Boolean {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    override suspend fun getLastKnownLocation(): LocationData? {
        return getCurrentLocation()
    }

    private fun Location.toLocationData(): LocationData {
        return LocationData(
            latitude = latitude,
            longitude = longitude,
            accuracy = accuracy,
            altitude = altitude,
            speed = speed,
            bearing = bearing,
            timestamp = time,
            provider = provider ?: "fused"
        )
    }
}