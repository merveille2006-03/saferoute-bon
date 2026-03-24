package com.saferoute.di;

import android.content.Context;
import androidx.room.Room;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.saferoute.data.local.database.SafeRouteDatabase;
import com.saferoute.data.repository.*;
import com.saferoute.domain.repository.*;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\'J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0012H\'J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0015H\'\u00a8\u0006\u0016"}, d2 = {"Lcom/saferoute/di/RepositoryModule;", "", "()V", "bindAlertRepository", "Lcom/saferoute/domain/repository/AlertRepository;", "impl", "Lcom/saferoute/data/repository/AlertRepositoryImpl;", "bindEmergencyContactRepository", "Lcom/saferoute/domain/repository/EmergencyContactRepository;", "Lcom/saferoute/data/repository/EmergencyContactRepositoryImpl;", "bindFallDetectionRepository", "Lcom/saferoute/domain/repository/FallDetectionRepository;", "Lcom/saferoute/data/repository/FallDetectionRepositoryImpl;", "bindLocationRepository", "Lcom/saferoute/domain/repository/LocationRepository;", "Lcom/saferoute/data/repository/LocationRepositoryImpl;", "bindSafeZoneRepository", "Lcom/saferoute/domain/repository/SafeZoneRepository;", "Lcom/saferoute/data/repository/SafeZoneRepositoryImpl;", "bindSettingsRepository", "Lcom/saferoute/domain/repository/SettingsRepository;", "Lcom/saferoute/data/repository/SettingsRepositoryImpl;", "app_release"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.saferoute.domain.repository.EmergencyContactRepository bindEmergencyContactRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.repository.EmergencyContactRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.saferoute.domain.repository.SafeZoneRepository bindSafeZoneRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.repository.SafeZoneRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.saferoute.domain.repository.SettingsRepository bindSettingsRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.repository.SettingsRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.saferoute.domain.repository.LocationRepository bindLocationRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.repository.LocationRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.saferoute.domain.repository.FallDetectionRepository bindFallDetectionRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.repository.FallDetectionRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.saferoute.domain.repository.AlertRepository bindAlertRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.repository.AlertRepositoryImpl impl);
}