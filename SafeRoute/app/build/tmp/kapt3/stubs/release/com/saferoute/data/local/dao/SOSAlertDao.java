package com.saferoute.data.local.dao;

import androidx.room.*;
import com.saferoute.data.local.entity.SOSAlertEntity;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00102\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00102\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00100\u00152\u0006\u0010\b\u001a\u00020\tH\'J\u0016\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0018\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u001e\u0010\u001c\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"Lcom/saferoute/data/local/dao/SOSAlertDao;", "", "deleteAlert", "", "alert", "Lcom/saferoute/data/local/entity/SOSAlertEntity;", "(Lcom/saferoute/data/local/entity/SOSAlertEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllAlerts", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlertById", "alertId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlertsByStatus", "", "status", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAlerts", "getAllAlertsFlow", "Lkotlinx/coroutines/flow/Flow;", "insertAlert", "updateAlert", "updateDeliveredCount", "count", "", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStatus", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
@androidx.room.Dao()
public abstract interface SOSAlertDao {
    
    @androidx.room.Query(value = "SELECT * FROM sos_alerts WHERE userId = :userId ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllAlerts(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.data.local.entity.SOSAlertEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sos_alerts WHERE userId = :userId ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.saferoute.data.local.entity.SOSAlertEntity>> getAllAlertsFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "SELECT * FROM sos_alerts WHERE id = :alertId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAlertById(long alertId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.data.local.entity.SOSAlertEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sos_alerts WHERE userId = :userId AND status = :status ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAlertsByStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.data.local.entity.SOSAlertEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAlert(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.entity.SOSAlertEntity alert, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateAlert(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.entity.SOSAlertEntity alert, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE sos_alerts SET status = :status WHERE id = :alertId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateStatus(long alertId, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE sos_alerts SET deliveredCount = :count WHERE id = :alertId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateDeliveredCount(long alertId, int count, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAlert(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.entity.SOSAlertEntity alert, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM sos_alerts WHERE userId = :userId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllAlerts(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}