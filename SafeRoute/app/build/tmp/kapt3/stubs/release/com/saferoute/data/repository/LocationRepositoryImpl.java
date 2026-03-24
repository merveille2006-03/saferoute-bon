package com.saferoute.data.repository;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.*;
import com.saferoute.domain.model.LocationData;
import com.saferoute.domain.repository.LocationRepository;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u0004\u0018\u00010\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u001eJ\f\u0010\u001f\u001a\u00020\n*\u00020 H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/saferoute/data/repository/LocationRepositoryImpl;", "Lcom/saferoute/domain/repository/LocationRepository;", "context", "Landroid/content/Context;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "(Landroid/content/Context;Lcom/google/android/gms/location/FusedLocationProviderClient;)V", "locationManager", "Landroid/location/LocationManager;", "getCurrentLocation", "Lcom/saferoute/domain/model/LocationData;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastKnownLocation", "getLocationHistory", "", "userId", "", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocationUpdates", "Lkotlinx/coroutines/flow/Flow;", "intervalMs", "", "isBackgroundLocationPermissionGranted", "", "isLocationPermissionGranted", "saveLocationHistory", "", "location", "(Lcom/saferoute/domain/model/LocationData;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toLocationData", "Landroid/location/Location;", "app_release"})
public final class LocationRepositoryImpl implements com.saferoute.domain.repository.LocationRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient = null;
    @org.jetbrains.annotations.NotNull()
    private final android.location.LocationManager locationManager = null;
    
    @javax.inject.Inject()
    public LocationRepositoryImpl(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getCurrentLocation(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.domain.model.LocationData> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.saferoute.domain.model.LocationData> getLocationUpdates(long intervalMs) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveLocationHistory(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.LocationData location, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getLocationHistory(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.domain.model.LocationData>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    public boolean isLocationPermissionGranted() {
        return false;
    }
    
    @java.lang.Override()
    public boolean isBackgroundLocationPermissionGranted() {
        return false;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getLastKnownLocation(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.domain.model.LocationData> $completion) {
        return null;
    }
    
    private final com.saferoute.domain.model.LocationData toLocationData(android.location.Location $this$toLocationData) {
        return null;
    }
}