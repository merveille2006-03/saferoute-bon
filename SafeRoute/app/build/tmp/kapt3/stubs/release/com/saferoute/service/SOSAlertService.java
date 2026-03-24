package com.saferoute.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.saferoute.R;
import com.saferoute.SafeRouteApplication;
import com.saferoute.domain.model.LocationData;
import com.saferoute.domain.model.SOSAlert;
import com.saferoute.domain.repository.AlertRepository;
import com.saferoute.domain.repository.LocationRepository;
import com.saferoute.presentation.MainActivity;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.*;
import timber.log.Timber;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 -2\u00020\u0001:\u0002-.B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000eH\u0002J\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u001aH\u0016J\b\u0010#\u001a\u00020\u001aH\u0016J\"\u0010$\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000eH\u0016J\b\u0010\'\u001a\u00020\u001aH\u0002J\b\u0010(\u001a\u00020\u001aH\u0002J\b\u0010)\u001a\u00020\u001aH\u0002J\b\u0010*\u001a\u00020\u001aH\u0002J\b\u0010+\u001a\u00020\u001aH\u0002J\u0010\u0010,\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u000eH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00060\nR\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/saferoute/service/SOSAlertService;", "Landroid/app/Service;", "()V", "alertRepository", "Lcom/saferoute/domain/repository/AlertRepository;", "getAlertRepository", "()Lcom/saferoute/domain/repository/AlertRepository;", "setAlertRepository", "(Lcom/saferoute/domain/repository/AlertRepository;)V", "binder", "Lcom/saferoute/service/SOSAlertService$LocalBinder;", "countDownTimer", "Landroid/os/CountDownTimer;", "countdownSeconds", "", "locationRepository", "Lcom/saferoute/domain/repository/LocationRepository;", "getLocationRepository", "()Lcom/saferoute/domain/repository/LocationRepository;", "setLocationRepository", "(Lcom/saferoute/domain/repository/LocationRepository;)V", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "userId", "", "cancelCountdown", "", "createCountdownNotification", "Landroid/app/Notification;", "seconds", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "sendAlert", "sendImmediateAlert", "showAlertFailedNotification", "showAlertSentNotification", "startCountdown", "updateNotification", "Companion", "LocalBinder", "app_release"})
public final class SOSAlertService extends android.app.Service {
    @javax.inject.Inject()
    public com.saferoute.domain.repository.AlertRepository alertRepository;
    @javax.inject.Inject()
    public com.saferoute.domain.repository.LocationRepository locationRepository;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.service.SOSAlertService.LocalBinder binder = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer countDownTimer;
    private int countdownSeconds = 5;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String userId = "default_user";
    public static final int NOTIFICATION_ID = 1003;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_START_COUNTDOWN = "action_start_countdown";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_CANCEL_COUNTDOWN = "action_cancel_countdown";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_SEND_IMMEDIATE = "action_send_immediate";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_COUNTDOWN = "extra_countdown";
    @org.jetbrains.annotations.NotNull()
    public static final com.saferoute.service.SOSAlertService.Companion Companion = null;
    
    public SOSAlertService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.domain.repository.AlertRepository getAlertRepository() {
        return null;
    }
    
    public final void setAlertRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.AlertRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.domain.repository.LocationRepository getLocationRepository() {
        return null;
    }
    
    public final void setLocationRepository(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.LocationRepository p0) {
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
    
    private final void startCountdown() {
    }
    
    private final void cancelCountdown() {
    }
    
    private final void sendImmediateAlert() {
    }
    
    private final void sendAlert() {
    }
    
    private final android.app.Notification createCountdownNotification(int seconds) {
        return null;
    }
    
    private final void updateNotification(int seconds) {
    }
    
    private final void showAlertSentNotification() {
    }
    
    private final void showAlertFailedNotification() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/saferoute/service/SOSAlertService$Companion;", "", "()V", "ACTION_CANCEL_COUNTDOWN", "", "ACTION_SEND_IMMEDIATE", "ACTION_START_COUNTDOWN", "EXTRA_COUNTDOWN", "NOTIFICATION_ID", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/saferoute/service/SOSAlertService$LocalBinder;", "Landroid/os/Binder;", "(Lcom/saferoute/service/SOSAlertService;)V", "getService", "Lcom/saferoute/service/SOSAlertService;", "app_release"})
    public final class LocalBinder extends android.os.Binder {
        
        public LocalBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.saferoute.service.SOSAlertService getService() {
            return null;
        }
    }
}