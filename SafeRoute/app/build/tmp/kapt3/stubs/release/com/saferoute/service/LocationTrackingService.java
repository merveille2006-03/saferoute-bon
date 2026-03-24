package com.saferoute.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.saferoute.R;
import com.saferoute.SafeRouteApplication;
import com.saferoute.domain.model.LocationData;
import com.saferoute.domain.repository.AlertRepository;
import com.saferoute.domain.repository.LocationRepository;
import com.saferoute.domain.repository.SafeZoneRepository;
import com.saferoute.domain.repository.SettingsRepository;
import com.saferoute.presentation.MainActivity;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.*;
import timber.log.Timber;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 ;2\u00020\u0001:\u0002;<B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010$\u001a\u00020%H\u0002J\u0012\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020+H\u0016J\"\u0010-\u001a\u00020.2\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020.H\u0016J\u0016\u00101\u001a\u00020+2\u0006\u00102\u001a\u000203H\u0082@\u00a2\u0006\u0002\u00104J\u001e\u00105\u001a\u00020+2\u0006\u00102\u001a\u0002032\u0006\u00106\u001a\u00020#H\u0082@\u00a2\u0006\u0002\u00107J\u0010\u00108\u001a\u00020+2\u0006\u00106\u001a\u00020#H\u0002J\b\u00109\u001a\u00020+H\u0002J\b\u0010:\u001a\u00020+H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00060\nR\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\rR\u001e\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00020\u001d8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"Lcom/saferoute/service/LocationTrackingService;", "Landroid/app/Service;", "()V", "alertRepository", "Lcom/saferoute/domain/repository/AlertRepository;", "getAlertRepository", "()Lcom/saferoute/domain/repository/AlertRepository;", "setAlertRepository", "(Lcom/saferoute/domain/repository/AlertRepository;)V", "binder", "Lcom/saferoute/service/LocationTrackingService$LocalBinder;", "lastSafeZoneId", "", "Ljava/lang/Long;", "locationRepository", "Lcom/saferoute/domain/repository/LocationRepository;", "getLocationRepository", "()Lcom/saferoute/domain/repository/LocationRepository;", "setLocationRepository", "(Lcom/saferoute/domain/repository/LocationRepository;)V", "safeZoneRepository", "Lcom/saferoute/domain/repository/SafeZoneRepository;", "getSafeZoneRepository", "()Lcom/saferoute/domain/repository/SafeZoneRepository;", "setSafeZoneRepository", "(Lcom/saferoute/domain/repository/SafeZoneRepository;)V", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "settingsRepository", "Lcom/saferoute/domain/repository/SettingsRepository;", "getSettingsRepository", "()Lcom/saferoute/domain/repository/SettingsRepository;", "setSettingsRepository", "(Lcom/saferoute/domain/repository/SettingsRepository;)V", "userId", "", "createNotification", "Landroid/app/Notification;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onStartCommand", "", "flags", "startId", "processLocation", "location", "Lcom/saferoute/domain/model/LocationData;", "(Lcom/saferoute/domain/model/LocationData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSafeZoneExitAlert", "zoneName", "(Lcom/saferoute/domain/model/LocationData;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showSafeZoneExitNotification", "startTracking", "stopTracking", "Companion", "LocalBinder", "app_release"})
public final class LocationTrackingService extends android.app.Service {
    @javax.inject.Inject()
    public com.saferoute.domain.repository.LocationRepository locationRepository;
    @javax.inject.Inject()
    public com.saferoute.domain.repository.SafeZoneRepository safeZoneRepository;
    @javax.inject.Inject()
    public com.saferoute.domain.repository.AlertRepository alertRepository;
    @javax.inject.Inject()
    public com.saferoute.domain.repository.SettingsRepository settingsRepository;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.service.LocationTrackingService.LocalBinder binder = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long lastSafeZoneId;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String userId = "default_user";
    public static final int NOTIFICATION_ID = 1002;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_START = "action_start";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_STOP = "action_stop";
    @org.jetbrains.annotations.NotNull()
    public static final com.saferoute.service.LocationTrackingService.Companion Companion = null;
    
    public LocationTrackingService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.domain.repository.LocationRepository getLocationRepository() {
        return null;
    }
    
    public final void setLocationRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.LocationRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.domain.repository.SafeZoneRepository getSafeZoneRepository() {
        return null;
    }
    
    public final void setSafeZoneRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.SafeZoneRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.domain.repository.AlertRepository getAlertRepository() {
        return null;
    }
    
    public final void setAlertRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.AlertRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.domain.repository.SettingsRepository getSettingsRepository() {
        return null;
    }
    
    public final void setSettingsRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.SettingsRepository p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void startTracking() {
    }
    
    private final void stopTracking() {
    }
    
    private final java.lang.Object processLocation(com.saferoute.domain.model.LocationData location, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object sendSafeZoneExitAlert(com.saferoute.domain.model.LocationData location, java.lang.String zoneName, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final android.app.Notification createNotification() {
        return null;
    }
    
    private final void showSafeZoneExitNotification(java.lang.String zoneName) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/saferoute/service/LocationTrackingService$Companion;", "", "()V", "ACTION_START", "", "ACTION_STOP", "NOTIFICATION_ID", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/saferoute/service/LocationTrackingService$LocalBinder;", "Landroid/os/Binder;", "(Lcom/saferoute/service/LocationTrackingService;)V", "getService", "Lcom/saferoute/service/LocationTrackingService;", "app_release"})
    public final class LocalBinder extends android.os.Binder {
        
        public LocalBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.saferoute.service.LocationTrackingService getService() {
            return null;
        }
    }
}