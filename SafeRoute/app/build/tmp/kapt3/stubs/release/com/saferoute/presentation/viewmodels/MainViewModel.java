package com.saferoute.presentation.viewmodels;

import android.app.Application;
import android.content.Intent;
import androidx.lifecycle.AndroidViewModel;
import com.saferoute.domain.model.*;
import com.saferoute.domain.repository.*;
import com.saferoute.service.FallDetectionService;
import com.saferoute.service.LocationTrackingService;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u0016\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u0017H\u0082@\u00a2\u0006\u0002\u00106J\u000e\u00107\u001a\u0002042\u0006\u00108\u001a\u00020\u001bJ\u000e\u00109\u001a\u000204H\u0082@\u00a2\u0006\u0002\u0010:J\b\u0010;\u001a\u000204H\u0014J\u0006\u0010<\u001a\u000204J\u001e\u0010=\u001a\u0002042\u0006\u00105\u001a\u00020\u00172\u0006\u0010>\u001a\u00020\u001fH\u0082@\u00a2\u0006\u0002\u0010?J\u0006\u0010@\u001a\u000204J\u0006\u0010A\u001a\u000204J\u0006\u0010B\u001a\u000204J\u0006\u0010C\u001a\u000204R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001e0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00130!\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n &*\u0004\u0018\u00010%0%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150!\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010#R\u0019\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170!\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010#R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00190!\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010#R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001b0!\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010#R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\u001b0!\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010#R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001e0!\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010#R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006D"}, d2 = {"Lcom/saferoute/presentation/viewmodels/MainViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "settingsRepository", "Lcom/saferoute/domain/repository/SettingsRepository;", "locationRepository", "Lcom/saferoute/domain/repository/LocationRepository;", "fallDetectionRepository", "Lcom/saferoute/domain/repository/FallDetectionRepository;", "safeZoneRepository", "Lcom/saferoute/domain/repository/SafeZoneRepository;", "alertRepository", "Lcom/saferoute/domain/repository/AlertRepository;", "contactRepository", "Lcom/saferoute/domain/repository/EmergencyContactRepository;", "(Landroid/app/Application;Lcom/saferoute/domain/repository/SettingsRepository;Lcom/saferoute/domain/repository/LocationRepository;Lcom/saferoute/domain/repository/FallDetectionRepository;Lcom/saferoute/domain/repository/SafeZoneRepository;Lcom/saferoute/domain/repository/AlertRepository;Lcom/saferoute/domain/repository/EmergencyContactRepository;)V", "_alertCount", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_currentFallEvent", "Lcom/saferoute/domain/model/FallEvent;", "_currentLocation", "Lcom/saferoute/domain/model/LocationData;", "_fallDetectionState", "Lcom/saferoute/domain/model/FallDetectionState;", "_isLoading", "", "_isServiceRunning", "_safeZones", "", "Lcom/saferoute/domain/model/SafeZone;", "alertCount", "Lkotlinx/coroutines/flow/StateFlow;", "getAlertCount", "()Lkotlinx/coroutines/flow/StateFlow;", "context", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "currentFallEvent", "getCurrentFallEvent", "currentLocation", "getCurrentLocation", "fallDetectionState", "getFallDetectionState", "isLoading", "isServiceRunning", "safeZones", "getSafeZones", "userId", "", "checkSafeZones", "", "location", "(Lcom/saferoute/domain/model/LocationData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmFall", "isRealFall", "initializeApp", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onCleared", "sendSOSAlert", "sendSafeZoneAlert", "zone", "(Lcom/saferoute/domain/model/LocationData;Lcom/saferoute/domain/model/SafeZone;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startFallDetectionService", "startLocationTrackingService", "stopFallDetectionService", "stopLocationTrackingService", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MainViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.domain.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.domain.repository.LocationRepository locationRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.domain.repository.FallDetectionRepository fallDetectionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.domain.repository.SafeZoneRepository safeZoneRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.domain.repository.AlertRepository alertRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.domain.repository.EmergencyContactRepository contactRepository = null;
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saferoute.domain.model.LocationData> _currentLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.LocationData> currentLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saferoute.domain.model.FallDetectionState> _fallDetectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.FallDetectionState> fallDetectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.saferoute.domain.model.SafeZone>> _safeZones = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.saferoute.domain.model.SafeZone>> safeZones = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isServiceRunning = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isServiceRunning = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _alertCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> alertCount = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = "default_user";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saferoute.domain.model.FallEvent> _currentFallEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.FallEvent> currentFallEvent = null;
    
    @javax.inject.Inject()
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.LocationRepository locationRepository, @org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.FallDetectionRepository fallDetectionRepository, @org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.SafeZoneRepository safeZoneRepository, @org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.AlertRepository alertRepository, @org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.EmergencyContactRepository contactRepository) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.LocationData> getCurrentLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.FallDetectionState> getFallDetectionState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.saferoute.domain.model.SafeZone>> getSafeZones() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isServiceRunning() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getAlertCount() {
        return null;
    }
    
    private final java.lang.Object initializeApp(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object checkSafeZones(com.saferoute.domain.model.LocationData location, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object sendSafeZoneAlert(com.saferoute.domain.model.LocationData location, com.saferoute.domain.model.SafeZone zone, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void startFallDetectionService() {
    }
    
    public final void stopFallDetectionService() {
    }
    
    public final void startLocationTrackingService() {
    }
    
    public final void stopLocationTrackingService() {
    }
    
    public final void sendSOSAlert() {
    }
    
    public final void confirmFall(boolean isRealFall) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.FallEvent> getCurrentFallEvent() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}