package com.saferoute.domain.repository;

import com.saferoute.domain.model.LocationData;
import com.saferoute.domain.model.SafeZone;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\n\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\n\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00130\u00172\u0006\u0010\n\u001a\u00020\u000bH&J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0011J \u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u001b"}, d2 = {"Lcom/saferoute/domain/repository/SafeZoneRepository;", "", "addSafeZone", "", "safeZone", "Lcom/saferoute/domain/model/SafeZone;", "(Lcom/saferoute/domain/model/SafeZone;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkSafeZoneExit", "location", "Lcom/saferoute/domain/model/LocationData;", "userId", "", "(Lcom/saferoute/domain/model/LocationData;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSafeZone", "", "deleteSafeZoneById", "zoneId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveSafeZones", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSafeZones", "getAllSafeZonesFlow", "Lkotlinx/coroutines/flow/Flow;", "getSafeZoneById", "isLocationInSafeZone", "updateSafeZone", "app_debug"})
public abstract interface SafeZoneRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllSafeZones(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.domain.model.SafeZone>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.saferoute.domain.model.SafeZone>> getAllSafeZonesFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSafeZoneById(long zoneId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.domain.model.SafeZone> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addSafeZone(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.SafeZone safeZone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateSafeZone(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.SafeZone safeZone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteSafeZone(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.SafeZone safeZone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteSafeZoneById(long zoneId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActiveSafeZones(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.domain.model.SafeZone>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isLocationInSafeZone(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.LocationData location, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.domain.model.SafeZone> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkSafeZoneExit(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.LocationData location, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.domain.model.SafeZone> $completion);
}