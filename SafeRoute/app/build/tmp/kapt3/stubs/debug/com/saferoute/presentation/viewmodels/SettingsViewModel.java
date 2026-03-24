package com.saferoute.presentation.viewmodels;

import androidx.lifecycle.ViewModel;
import com.saferoute.domain.model.AppSettings;
import com.saferoute.domain.repository.SettingsRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\tJ\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\tJ\u000e\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\tJ\u000e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\tJ\u000e\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\tJ\u000e\u0010\"\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\tJ\u000e\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020 J\u000e\u0010%\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\tJ\u001c\u0010&\u001a\u00020\u00152\u0012\u0010\'\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0(H\u0002J\u000e\u0010)\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\tJ\u000e\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020,J\u000e\u0010-\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\tR\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/saferoute/presentation/viewmodels/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepository", "Lcom/saferoute/domain/repository/SettingsRepository;", "(Lcom/saferoute/domain/repository/SettingsRepository;)V", "_errorMessage", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_isLoading", "", "_settings", "Lcom/saferoute/domain/model/AppSettings;", "errorMessage", "Lkotlinx/coroutines/flow/StateFlow;", "getErrorMessage", "()Lkotlinx/coroutines/flow/StateFlow;", "isLoading", "settings", "getSettings", "userId", "clearError", "", "loadSettings", "resetSettings", "updateAutoSendOnNoResponse", "enabled", "updateBiometricRequired", "required", "updateBluetoothEnabled", "updateFallDetectionEnabled", "updateFallDetectionSensitivity", "sensitivity", "", "updateIncludeLocationInAlerts", "updateLocationTrackingEnabled", "updateSOSCountdown", "seconds", "updateSafeZoneMonitoringEnabled", "updateSettings", "update", "Lkotlin/Function1;", "updateSoundEnabled", "updateTrackingInterval", "intervalMs", "", "updateVibrationEnabled", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.domain.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = "default_user";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saferoute.domain.model.AppSettings> _settings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.AppSettings> settings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> errorMessage = null;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.SettingsRepository settingsRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.AppSettings> getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getErrorMessage() {
        return null;
    }
    
    private final void loadSettings() {
    }
    
    public final void updateFallDetectionEnabled(boolean enabled) {
    }
    
    public final void updateLocationTrackingEnabled(boolean enabled) {
    }
    
    public final void updateSafeZoneMonitoringEnabled(boolean enabled) {
    }
    
    public final void updateBluetoothEnabled(boolean enabled) {
    }
    
    public final void updateBiometricRequired(boolean required) {
    }
    
    public final void updateFallDetectionSensitivity(int sensitivity) {
    }
    
    public final void updateSOSCountdown(int seconds) {
    }
    
    public final void updateAutoSendOnNoResponse(boolean enabled) {
    }
    
    public final void updateIncludeLocationInAlerts(boolean enabled) {
    }
    
    public final void updateSoundEnabled(boolean enabled) {
    }
    
    public final void updateVibrationEnabled(boolean enabled) {
    }
    
    public final void updateTrackingInterval(long intervalMs) {
    }
    
    public final void resetSettings() {
    }
    
    private final void updateSettings(kotlin.jvm.functions.Function1<? super com.saferoute.domain.model.AppSettings, com.saferoute.domain.model.AppSettings> update) {
    }
    
    public final void clearError() {
    }
}