package com.saferoute.domain.repository;

import com.saferoute.domain.model.FallDetectionState;
import com.saferoute.domain.model.FallEvent;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J\u000e\u0010\n\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u00a6@\u00a2\u0006\u0002\u0010\u0012J&\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u00a6@\u00a2\u0006\u0002\u0010\u0019J\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00140\u001b2\u0006\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u001c\u001a\u00020\u0011H&J\u0016\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u0018H\u00a6@\u00a2\u0006\u0002\u0010\"J\u000e\u0010#\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\fJ\u000e\u0010$\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010%\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u001fR\u001a\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\u0006\u00a8\u0006&"}, d2 = {"Lcom/saferoute/domain/repository/FallDetectionRepository;", "", "currentFallEvent", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/saferoute/domain/model/FallEvent;", "getCurrentFallEvent", "()Lkotlinx/coroutines/flow/StateFlow;", "fallDetectionState", "Lcom/saferoute/domain/model/FallDetectionState;", "getFallDetectionState", "cancelCurrentAlert", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmFall", "eventId", "", "isRealFall", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFallHistory", "", "userId", "", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFallHistoryFlow", "Lkotlinx/coroutines/flow/Flow;", "isAccelerometerAvailable", "recordFallEvent", "event", "(Lcom/saferoute/domain/model/FallEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setSensitivity", "level", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startMonitoring", "stopMonitoring", "updateFallEvent", "app_debug"})
public abstract interface FallDetectionRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.FallDetectionState> getFallDetectionState();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.FallEvent> getCurrentFallEvent();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object startMonitoring(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object stopMonitoring(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object recordFallEvent(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.FallEvent event, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object confirmFall(long eventId, boolean isRealFall, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object cancelCurrentAlert(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFallHistory(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.domain.model.FallEvent>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.saferoute.domain.model.FallEvent>> getFallHistoryFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateFallEvent(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.FallEvent event, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    public abstract boolean isAccelerometerAvailable();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setSensitivity(int level, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}