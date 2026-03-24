package com.saferoute.data.repository;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.saferoute.data.local.dao.FallEventDao;
import com.saferoute.data.local.entity.FallEventEntity;
import com.saferoute.domain.model.FallDetectionState;
import com.saferoute.domain.model.FallEvent;
import com.saferoute.domain.repository.FallDetectionRepository;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.*;
import kotlinx.coroutines.flow.*;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010%\u001a\u00020\u001eH\u0002J\u000e\u0010&\u001a\u00020\'H\u0096@\u00a2\u0006\u0002\u0010(J\u001e\u0010)\u001a\u00020\'2\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\u001cH\u0096@\u00a2\u0006\u0002\u0010,J\u0010\u0010-\u001a\u00020\'2\u0006\u0010.\u001a\u00020\"H\u0002J$\u0010/\u001a\b\u0012\u0004\u0012\u00020\n002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0096@\u00a2\u0006\u0002\u00105J\u001c\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n00072\u0006\u00101\u001a\u000202H\u0016J\b\u00108\u001a\u00020\u0012H\u0002J\b\u00109\u001a\u00020\u001cH\u0016J\u001a\u0010:\u001a\u00020\'2\b\u0010;\u001a\u0004\u0018\u00010\u000e2\u0006\u0010<\u001a\u000204H\u0016J\u0012\u0010=\u001a\u00020\'2\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J\u0016\u0010@\u001a\u00020\u001e2\u0006\u0010>\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010AJ\u0016\u0010B\u001a\u00020\'2\u0006\u0010C\u001a\u000204H\u0096@\u00a2\u0006\u0002\u0010DJ\u0010\u0010E\u001a\u00020\'2\u0006\u0010F\u001a\u00020\nH\u0002J\u000e\u0010G\u001a\u00020\'H\u0096@\u00a2\u0006\u0002\u0010(J\u000e\u0010H\u001a\u00020\'H\u0096@\u00a2\u0006\u0002\u0010(J\u0016\u0010I\u001a\u00020\'2\u0006\u0010>\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010AR\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u000f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006J"}, d2 = {"Lcom/saferoute/data/repository/FallDetectionRepositoryImpl;", "Lcom/saferoute/domain/repository/FallDetectionRepository;", "Landroid/hardware/SensorEventListener;", "context", "Landroid/content/Context;", "fallEventDao", "Lcom/saferoute/data/local/dao/FallEventDao;", "(Landroid/content/Context;Lcom/saferoute/data/local/dao/FallEventDao;)V", "_currentFallEvent", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/saferoute/domain/model/FallEvent;", "_fallDetectionState", "Lcom/saferoute/domain/model/FallDetectionState;", "accelerometer", "Landroid/hardware/Sensor;", "accelerometerBuffer", "", "Lkotlin/Triple;", "", "confirmationJob", "Lkotlinx/coroutines/Job;", "currentFallEvent", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentFallEvent", "()Lkotlinx/coroutines/flow/StateFlow;", "fallDetectionState", "getFallDetectionState", "isMonitoring", "", "lastFallTime", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "sensitivityThreshold", "", "sensorManager", "Landroid/hardware/SensorManager;", "analyzeFallDuration", "cancelCurrentAlert", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmFall", "eventId", "isRealFall", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectFall", "impactForce", "getFallHistory", "", "userId", "", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFallHistoryFlow", "Lkotlinx/coroutines/flow/Flow;", "getImpactThreshold", "isAccelerometerAvailable", "onAccuracyChanged", "sensor", "accuracy", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "recordFallEvent", "(Lcom/saferoute/domain/model/FallEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setSensitivity", "level", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startConfirmationTimeout", "fallEvent", "startMonitoring", "stopMonitoring", "updateFallEvent", "app_debug"})
public final class FallDetectionRepositoryImpl implements com.saferoute.domain.repository.FallDetectionRepository, android.hardware.SensorEventListener {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.data.local.dao.FallEventDao fallEventDao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.hardware.SensorManager sensorManager = null;
    @org.jetbrains.annotations.Nullable()
    private final android.hardware.Sensor accelerometer = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saferoute.domain.model.FallDetectionState> _fallDetectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.FallDetectionState> fallDetectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saferoute.domain.model.FallEvent> _currentFallEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.FallEvent> currentFallEvent = null;
    private double sensitivityThreshold = 2.5;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job confirmationJob;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<kotlin.Triple<java.lang.Float, java.lang.Float, java.lang.Float>> accelerometerBuffer = null;
    private long lastFallTime = 0L;
    private boolean isMonitoring = false;
    
    @javax.inject.Inject()
    public FallDetectionRepositoryImpl(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.dao.FallEventDao fallEventDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.FallDetectionState> getFallDetectionState() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.saferoute.domain.model.FallEvent> getCurrentFallEvent() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object startMonitoring(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object stopMonitoring(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    public void onSensorChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.SensorEvent event) {
    }
    
    @java.lang.Override()
    public void onAccuracyChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.Sensor sensor, int accuracy) {
    }
    
    private final void detectFall(double impactForce) {
    }
    
    private final long analyzeFallDuration() {
        return 0L;
    }
    
    private final void startConfirmationTimeout(com.saferoute.domain.model.FallEvent fallEvent) {
    }
    
    private final float getImpactThreshold() {
        return 0.0F;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object recordFallEvent(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.FallEvent event, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object confirmFall(long eventId, boolean isRealFall, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object cancelCurrentAlert(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getFallHistory(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.domain.model.FallEvent>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.saferoute.domain.model.FallEvent>> getFallHistoryFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateFallEvent(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.FallEvent event, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    public boolean isAccelerometerAvailable() {
        return false;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setSensitivity(int level, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}