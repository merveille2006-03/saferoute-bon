package com.saferoute.data.repository;

import com.saferoute.data.local.dao.SafeZoneDao;
import com.saferoute.data.local.entity.SafeZoneEntity;
import com.saferoute.domain.model.LocationData;
import com.saferoute.domain.model.SafeZone;
import com.saferoute.domain.repository.SafeZoneRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.math.*;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0002J \u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00192\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u001aJ\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u00192\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u001aJ\u001c\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00190\u001d2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u001e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u0017J \u0010\u001f\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010 \u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/saferoute/data/repository/SafeZoneRepositoryImpl;", "Lcom/saferoute/domain/repository/SafeZoneRepository;", "safeZoneDao", "Lcom/saferoute/data/local/dao/SafeZoneDao;", "(Lcom/saferoute/data/local/dao/SafeZoneDao;)V", "addSafeZone", "", "safeZone", "Lcom/saferoute/domain/model/SafeZone;", "(Lcom/saferoute/domain/model/SafeZone;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateDistance", "", "location", "Lcom/saferoute/domain/model/LocationData;", "zone", "checkSafeZoneExit", "userId", "", "(Lcom/saferoute/domain/model/LocationData;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSafeZone", "", "deleteSafeZoneById", "zoneId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveSafeZones", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSafeZones", "getAllSafeZonesFlow", "Lkotlinx/coroutines/flow/Flow;", "getSafeZoneById", "isLocationInSafeZone", "updateSafeZone", "app_release"})
public final class SafeZoneRepositoryImpl implements com.saferoute.domain.repository.SafeZoneRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.data.local.dao.SafeZoneDao safeZoneDao = null;
    
    @javax.inject.Inject()
    public SafeZoneRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.dao.SafeZoneDao safeZoneDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllSafeZones(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.domain.model.SafeZone>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.saferoute.domain.model.SafeZone>> getAllSafeZonesFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getSafeZoneById(long zoneId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.domain.model.SafeZone> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addSafeZone(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.SafeZone safeZone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateSafeZone(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.SafeZone safeZone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteSafeZone(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.SafeZone safeZone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteSafeZoneById(long zoneId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getActiveSafeZones(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.domain.model.SafeZone>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object isLocationInSafeZone(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.LocationData location, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.domain.model.SafeZone> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object checkSafeZoneExit(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.LocationData location, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.domain.model.SafeZone> $completion) {
        return null;
    }
    
    private final double calculateDistance(com.saferoute.domain.model.LocationData location, com.saferoute.domain.model.SafeZone zone) {
        return 0.0;
    }
}