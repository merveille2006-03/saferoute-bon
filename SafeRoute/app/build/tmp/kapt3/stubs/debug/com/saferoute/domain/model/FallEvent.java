package com.saferoute.domain.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import kotlinx.serialization.Serializable;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b(\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 82\u00020\u0001:\u00018Bw\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0012J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\t\u0010,\u001a\u00020\nH\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u0010\u0010/\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u00100\u001a\u00020\rH\u00c6\u0003J\u0086\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u00102J\u0013\u00103\u001a\u00020\r2\b\u00104\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00105\u001a\u000206H\u00d6\u0001J\t\u00107\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u000f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\f\u0010\u001dR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b#\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\"\u00a8\u00069"}, d2 = {"Lcom/saferoute/domain/model/FallEvent;", "", "id", "", "userId", "", "timestamp", "location", "Lcom/saferoute/domain/model/LocationData;", "impactForce", "", "fallDuration", "isConfirmed", "", "responseTime", "alertSent", "alertSentAt", "notes", "(JLjava/lang/String;JLcom/saferoute/domain/model/LocationData;DJLjava/lang/Boolean;Ljava/lang/Long;ZLjava/lang/Long;Ljava/lang/String;)V", "getAlertSent", "()Z", "getAlertSentAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFallDuration", "()J", "getId", "getImpactForce", "()D", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLocation", "()Lcom/saferoute/domain/model/LocationData;", "getNotes", "()Ljava/lang/String;", "getResponseTime", "getTimestamp", "getUserId", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;JLcom/saferoute/domain/model/LocationData;DJLjava/lang/Boolean;Ljava/lang/Long;ZLjava/lang/Long;Ljava/lang/String;)Lcom/saferoute/domain/model/FallEvent;", "equals", "other", "hashCode", "", "toString", "Companion", "app_debug"})
@androidx.room.Entity(tableName = "fall_events")
public final class FallEvent {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = null;
    private final long timestamp = 0L;
    @org.jetbrains.annotations.Nullable()
    private final com.saferoute.domain.model.LocationData location = null;
    private final double impactForce = 0.0;
    private final long fallDuration = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean isConfirmed = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long responseTime = null;
    private final boolean alertSent = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long alertSentAt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String notes = null;
    public static final long RESPONSE_TIMEOUT_MS = 30000L;
    public static final double MIN_IMPACT_FORCE = 2.5;
    public static final int FALL_DURATION_THRESHOLD = 200;
    @org.jetbrains.annotations.NotNull()
    public static final com.saferoute.domain.model.FallEvent.Companion Companion = null;
    
    public FallEvent(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, long timestamp, @org.jetbrains.annotations.Nullable()
    com.saferoute.domain.model.LocationData location, double impactForce, long fallDuration, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isConfirmed, @org.jetbrains.annotations.Nullable()
    java.lang.Long responseTime, boolean alertSent, @org.jetbrains.annotations.Nullable()
    java.lang.Long alertSentAt, @org.jetbrains.annotations.Nullable()
    java.lang.String notes) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserId() {
        return null;
    }
    
    public final long getTimestamp() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.saferoute.domain.model.LocationData getLocation() {
        return null;
    }
    
    public final double getImpactForce() {
        return 0.0;
    }
    
    public final long getFallDuration() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isConfirmed() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getResponseTime() {
        return null;
    }
    
    public final boolean getAlertSent() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getAlertSentAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNotes() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.saferoute.domain.model.LocationData component4() {
        return null;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final long component6() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component8() {
        return null;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.saferoute.domain.model.FallEvent copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, long timestamp, @org.jetbrains.annotations.Nullable()
    com.saferoute.domain.model.LocationData location, double impactForce, long fallDuration, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isConfirmed, @org.jetbrains.annotations.Nullable()
    java.lang.Long responseTime, boolean alertSent, @org.jetbrains.annotations.Nullable()
    java.lang.Long alertSentAt, @org.jetbrains.annotations.Nullable()
    java.lang.String notes) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/saferoute/domain/model/FallEvent$Companion;", "", "()V", "FALL_DURATION_THRESHOLD", "", "MIN_IMPACT_FORCE", "", "RESPONSE_TIMEOUT_MS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}