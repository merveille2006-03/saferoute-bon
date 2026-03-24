package com.saferoute.data.local.dao;

import androidx.room.*;
import com.saferoute.data.local.entity.FallEventEntity;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00100\u00152\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0017\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ \u0010\u0018\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0019\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u001e\u0010\u001b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001dH\u00a7@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006 "}, d2 = {"Lcom/saferoute/data/local/dao/FallEventDao;", "", "deleteAllFallEvents", "", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFallEvent", "event", "Lcom/saferoute/data/local/entity/FallEventEntity;", "(Lcom/saferoute/data/local/entity/FallEventEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFallEventById", "eventId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFallHistory", "", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFallHistoryFlow", "Lkotlinx/coroutines/flow/Flow;", "getPendingFallEvent", "insertFallEvent", "markAlertSent", "timestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateConfirmation", "isConfirmed", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateFallEvent", "app_debug"})
@androidx.room.Dao()
public abstract interface FallEventDao {
    
    @androidx.room.Query(value = "SELECT * FROM fall_events WHERE userId = :userId ORDER BY timestamp DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFallHistory(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.data.local.entity.FallEventEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM fall_events WHERE userId = :userId ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.saferoute.data.local.entity.FallEventEntity>> getFallHistoryFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "SELECT * FROM fall_events WHERE id = :eventId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFallEventById(long eventId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.data.local.entity.FallEventEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM fall_events WHERE userId = :userId AND alertSent = 0 AND isConfirmed IS NULL ORDER BY timestamp DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPendingFallEvent(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.data.local.entity.FallEventEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertFallEvent(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.entity.FallEventEntity event, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateFallEvent(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.entity.FallEventEntity event, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE fall_events SET isConfirmed = :isConfirmed WHERE id = :eventId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateConfirmation(long eventId, boolean isConfirmed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE fall_events SET alertSent = 1, alertSentAt = :timestamp WHERE id = :eventId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAlertSent(long eventId, long timestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteFallEvent(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.entity.FallEventEntity event, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM fall_events WHERE userId = :userId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllFallEvents(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}