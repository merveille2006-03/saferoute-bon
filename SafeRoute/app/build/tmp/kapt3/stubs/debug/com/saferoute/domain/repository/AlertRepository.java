package com.saferoute.domain.repository;

import com.saferoute.domain.model.LocationData;
import com.saferoute.domain.model.SOSAlert;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\r2\u0006\u0010\t\u001a\u00020\nH&J:\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J2\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u0018\u001a\u00020\nH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0019\u0010\u001aJB\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\n2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\nH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b \u0010!J\u000e\u0010\"\u001a\u00020#H\u00a6@\u00a2\u0006\u0002\u0010$J\u001e\u0010%\u001a\u00020&2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010(\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006)"}, d2 = {"Lcom/saferoute/domain/repository/AlertRepository;", "", "getAlertById", "Lcom/saferoute/domain/model/SOSAlert;", "alertId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlertHistory", "", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlertHistoryFlow", "Lkotlinx/coroutines/flow/Flow;", "sendEmailAlert", "Lkotlin/Result;", "", "emails", "subject", "body", "sendEmailAlert-BWLJW6A", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSMSAlert", "phoneNumbers", "message", "sendSMSAlert-0E7RQCE", "(Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSOSAlert", "location", "Lcom/saferoute/domain/model/LocationData;", "triggerType", "customMessage", "sendSOSAlert-yxL6bBk", "(Ljava/lang/String;Lcom/saferoute/domain/model/LocationData;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "testAlertSystem", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAlertStatus", "", "status", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface AlertRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAlertHistory(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.domain.model.SOSAlert>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.saferoute.domain.model.SOSAlert>> getAlertHistoryFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAlertById(long alertId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.domain.model.SOSAlert> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateAlertStatus(long alertId, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object testAlertSystem(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}