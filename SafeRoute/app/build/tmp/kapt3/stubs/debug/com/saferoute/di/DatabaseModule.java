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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u0004H\u0007\u00a8\u0006\u0010"}, d2 = {"Lcom/saferoute/di/DatabaseModule;", "", "()V", "provideDatabase", "Lcom/saferoute/data/local/database/SafeRouteDatabase;", "context", "Landroid/content/Context;", "provideEmergencyContactDao", "Lcom/saferoute/data/local/dao/EmergencyContactDao;", "database", "provideFallEventDao", "Lcom/saferoute/data/local/dao/FallEventDao;", "provideSOSAlertDao", "Lcom/saferoute/data/local/dao/SOSAlertDao;", "provideSafeZoneDao", "Lcom/saferoute/data/local/dao/SafeZoneDao;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.saferoute.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.data.local.database.SafeRouteDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.data.local.dao.EmergencyContactDao provideEmergencyContactDao(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.database.SafeRouteDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.data.local.dao.SafeZoneDao provideSafeZoneDao(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.database.SafeRouteDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.data.local.dao.FallEventDao provideFallEventDao(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.database.SafeRouteDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.data.local.dao.SOSAlertDao provideSOSAlertDao(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.database.SafeRouteDatabase database) {
        return null;
    }
}