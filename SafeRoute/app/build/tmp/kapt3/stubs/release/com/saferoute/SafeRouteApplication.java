package com.saferoute;

import com.saferoute.BuildConfig;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;

@dagger.hilt.android.HiltAndroidApp()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016\u00a8\u0006\u0007"}, d2 = {"Lcom/saferoute/SafeRouteApplication;", "Landroid/app/Application;", "()V", "createNotificationChannels", "", "onCreate", "Companion", "app_release"})
public final class SafeRouteApplication extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_FALL_DETECTION = "fall_detection_channel";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_LOCATION_TRACKING = "location_tracking_channel";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_SOS_ALERT = "sos_alert_channel";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_SAFE_ZONE = "safe_zone_channel";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_BLUETOOTH = "bluetooth_channel";
    @org.jetbrains.annotations.NotNull()
    public static final com.saferoute.SafeRouteApplication.Companion Companion = null;
    
    public SafeRouteApplication() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void createNotificationChannels() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/saferoute/SafeRouteApplication$Companion;", "", "()V", "CHANNEL_BLUETOOTH", "", "CHANNEL_FALL_DETECTION", "CHANNEL_LOCATION_TRACKING", "CHANNEL_SAFE_ZONE", "CHANNEL_SOS_ALERT", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}