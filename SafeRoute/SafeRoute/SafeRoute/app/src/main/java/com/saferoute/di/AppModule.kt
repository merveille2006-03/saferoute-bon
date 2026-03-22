package com.saferoute.di

import android.content.Context
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.saferoute.data.local.database.SafeRouteDatabase
import com.saferoute.data.repository.*
import com.saferoute.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEmergencyContactRepository(
        impl: EmergencyContactRepositoryImpl
    ): EmergencyContactRepository

    @Binds
    @Singleton
    abstract fun bindSafeZoneRepository(
        impl: SafeZoneRepositoryImpl
    ): SafeZoneRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository

    @Binds
    @Singleton
    abstract fun bindLocationRepository(
        impl: LocationRepositoryImpl
    ): LocationRepository

    @Binds
    @Singleton
    abstract fun bindFallDetectionRepository(
        impl: FallDetectionRepositoryImpl
    ): FallDetectionRepository

    @Binds
    @Singleton
    abstract fun bindAlertRepository(
        impl: AlertRepositoryImpl
    ): AlertRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SafeRouteDatabase {
        return Room.databaseBuilder(
            context,
            SafeRouteDatabase::class.java,
            SafeRouteDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideEmergencyContactDao(database: SafeRouteDatabase) = database.emergencyContactDao()

    @Provides
    fun provideSafeZoneDao(database: SafeRouteDatabase) = database.safeZoneDao()

    @Provides
    fun provideFallEventDao(database: SafeRouteDatabase) = database.fallEventDao()

    @Provides
    fun provideSOSAlertDao(database: SafeRouteDatabase) = database.sosAlertDao()
}

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideFusedLocationClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }
}