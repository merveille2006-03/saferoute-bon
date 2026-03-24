package com.saferoute.presentation.viewmodels;

import androidx.lifecycle.ViewModel;
import com.saferoute.domain.model.LocationData;
import com.saferoute.domain.model.SafeZone;
import com.saferoute.domain.repository.LocationRepository;
import com.saferoute.domain.repository.SafeZoneRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J<\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\rJ\"\u0010$\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020\u000bJ\u0018\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\t2\u0006\u0010\'\u001a\u00020\u0010H\u0002J\u0006\u0010(\u001a\u00020\u001cJ\u000e\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\u0010J\u0010\u0010+\u001a\u0004\u0018\u00010\u00102\u0006\u0010&\u001a\u00020\tJ\b\u0010,\u001a\u00020\u001cH\u0002J\b\u0010-\u001a\u00020\u001cH\u0002J\u0006\u0010.\u001a\u00020\u001cJ\u000e\u0010/\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\u0010J\u000e\u00100\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\u0010R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u000e\u0010\u001a\u001a\u00020\u000bX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/saferoute/presentation/viewmodels/SafeZonesViewModel;", "Landroidx/lifecycle/ViewModel;", "safeZoneRepository", "Lcom/saferoute/domain/repository/SafeZoneRepository;", "locationRepository", "Lcom/saferoute/domain/repository/LocationRepository;", "(Lcom/saferoute/domain/repository/SafeZoneRepository;Lcom/saferoute/domain/repository/LocationRepository;)V", "_currentLocation", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/saferoute/domain/model/LocationData;", "_errorMessage", "", "_isLoading", "", "_safeZones", "", "Lcom/saferoute/domain/model/SafeZone;", "currentLocation", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentLocation", "()Lkotlinx/coroutines/flow/StateFlow;", "errorMessage", "getErrorMessage", "isLoading", "safeZones", "getSafeZones", "userId", "addSafeZone", "", "name", "latitude", "", "longitude", "radius", "type", "alertOnExit", "addSafeZoneAtCurrentLocation", "calculateDistance", "location", "zone", "clearError", "deleteSafeZone", "safeZone", "isLocationInSafeZone", "loadCurrentLocation", "loadSafeZones", "refreshLocation", "toggleSafeZoneActive", "updateSafeZone", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SafeZonesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.domain.repository.SafeZoneRepository safeZoneRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.domain.repository.LocationRepository locationRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = "default_user";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.saferoute.domain.model.SafeZone>> _safeZones = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.saferoute.domain.model.SafeZone>> safeZones = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saferoute.domain.model.LocationData> _currentLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.LocationData> currentLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> errorMessage = null;
    
    @javax.inject.Inject()
    public SafeZonesViewModel(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.SafeZoneRepository safeZoneRepository, @org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.LocationRepository locationRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.saferoute.domain.model.SafeZone>> getSafeZones() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.LocationData> getCurrentLocation() {
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
    
    private final void loadSafeZones() {
    }
    
    private final void loadCurrentLocation() {
    }
    
    public final void addSafeZone(@org.jetbrains.annotations.NotNull()
    java.lang.String name, double latitude, double longitude, double radius, @org.jetbrains.annotations.NotNull()
    java.lang.String type, boolean alertOnExit) {
    }
    
    public final void addSafeZoneAtCurrentLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String name, double radius, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
    }
    
    public final void updateSafeZone(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.SafeZone safeZone) {
    }
    
    public final void deleteSafeZone(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.SafeZone safeZone) {
    }
    
    public final void toggleSafeZoneActive(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.SafeZone safeZone) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.saferoute.domain.model.SafeZone isLocationInSafeZone(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.LocationData location) {
        return null;
    }
    
    private final double calculateDistance(com.saferoute.domain.model.LocationData location, com.saferoute.domain.model.SafeZone zone) {
        return 0.0;
    }
    
    public final void refreshLocation() {
    }
    
    public final void clearError() {
    }
}