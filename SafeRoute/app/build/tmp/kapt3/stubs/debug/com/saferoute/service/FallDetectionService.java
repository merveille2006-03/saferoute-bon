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
import com.saferoute.domain.model.FallDetectionState;
import com.saferoute.domain.model.FallEvent;
import com.saferoute.domain.model.LocationData;
import com.saferoute.domain.repository.AlertRepository;
import com.saferoute.domain.repository.FallDetectionRepository;
import com.saferoute.domain.repository.LocationRepository;
import com.saferoute.presentation.MainActivity;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.*;
import timber.log.Timber;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\b\u0007\u0018\u0000 /2\u00020\u0001:\u0002/0B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0012\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u001aH\u0016J\b\u0010$\u001a\u00020\u001aH\u0016J\"\u0010%\u001a\u00020&2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\'\u001a\u00020&2\u0006\u0010(\u001a\u00020&H\u0016J\u000e\u0010)\u001a\u00020\u001aH\u0082@\u00a2\u0006\u0002\u0010*J\b\u0010+\u001a\u00020\u001aH\u0002J\b\u0010,\u001a\u00020\u001aH\u0002J\b\u0010-\u001a\u00020\u001aH\u0002J\b\u0010.\u001a\u00020\u001aH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00060\nR\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/saferoute/service/FallDetectionService;", "Landroid/app/Service;", "()V", "alertRepository", "Lcom/saferoute/domain/repository/AlertRepository;", "getAlertRepository", "()Lcom/saferoute/domain/repository/AlertRepository;", "setAlertRepository", "(Lcom/saferoute/domain/repository/AlertRepository;)V", "binder", "Lcom/saferoute/service/FallDetectionService$LocalBinder;", "fallDetectionRepository", "Lcom/saferoute/domain/repository/FallDetectionRepository;", "getFallDetectionRepository", "()Lcom/saferoute/domain/repository/FallDetectionRepository;", "setFallDetectionRepository", "(Lcom/saferoute/domain/repository/FallDetectionRepository;)V", "locationRepository", "Lcom/saferoute/domain/repository/LocationRepository;", "getLocationRepository", "()Lcom/saferoute/domain/repository/LocationRepository;", "setLocationRepository", "(Lcom/saferoute/domain/repository/LocationRepository;)V", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "confirmFall", "", "isRealFall", "", "createNotification", "Landroid/app/Notification;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "sendFallAlert", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showAlertSentNotification", "showFallConfirmationNotification", "startFallDetection", "stopFallDetection", "Companion", "LocalBinder", "app_debug"})
public final class FallDetectionService extends android.app.Service {
    @javax.inject.Inject()
    public com.saferoute.domain.repository.FallDetectionRepository fallDetectionRepository;
    @javax.inject.Inject()
    public com.saferoute.domain.repository.LocationRepository locationRepository;
    @javax.inject.Inject()
    public com.saferoute.domain.repository.AlertRepository alertRepository;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.service.FallDetectionService.LocalBinder binder = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    public static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_START = "action_start";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_STOP = "action_stop";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_CONFIRM_FALL = "action_confirm_fall";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_CANCEL_FALL = "action_cancel_fall";
    @org.jetbrains.annotations.NotNull()
    public static final com.saferoute.service.FallDetectionService.Companion Companion = null;
    
    public FallDetectionService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.domain.repository.FallDetectionRepository getFallDetectionRepository() {
        return null;
    }
    
    public final void setFallDetectionRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.FallDetectionRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.domain.repository.LocationRepository getLocationRepository() {
        return null;
    }
    
    public final void setLocationRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.LocationRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.domain.repository.AlertRepository getAlertRepository() {
        return null;
    }
    
    public final void setAlertRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.AlertRepository p0) {
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
    
    private final void startFallDetection() {
    }
    
    private final void stopFallDetection() {
    }
    
    private final void confirmFall(boolean isRealFall) {
    }
    
    private final java.lang.Object sendFallAlert(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final android.app.Notification createNotification() {
        return null;
    }
    
    private final void showFallConfirmationNotification() {
    }
    
    private final void showAlertSentNotification() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/saferoute/service/FallDetectionService$Companion;", "", "()V", "ACTION_CANCEL_FALL", "", "ACTION_CONFIRM_FALL", "ACTION_START", "ACTION_STOP", "NOTIFICATION_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/saferoute/service/FallDetectionService$LocalBinder;", "Landroid/os/Binder;", "(Lcom/saferoute/service/FallDetectionService;)V", "getService", "Lcom/saferoute/service/FallDetectionService;", "app_debug"})
    public final class LocalBinder extends android.os.Binder {
        
        public LocalBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.saferoute.service.FallDetectionService getService() {
            return null;
        }
    }
}