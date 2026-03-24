package com.saferoute.domain.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import kotlinx.serialization.Serializable;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lcom/saferoute/domain/model/FallDetectionState;", "", "(Ljava/lang/String;I)V", "IDLE", "DETECTING", "CONFIRMATION_REQUIRED", "CONFIRMED", "CANCELLED", "ALERT_SENT", "app_debug"})
public enum FallDetectionState {
    /*public static final*/ IDLE /* = new IDLE() */,
    /*public static final*/ DETECTING /* = new DETECTING() */,
    /*public static final*/ CONFIRMATION_REQUIRED /* = new CONFIRMATION_REQUIRED() */,
    /*public static final*/ CONFIRMED /* = new CONFIRMED() */,
    /*public static final*/ CANCELLED /* = new CANCELLED() */,
    /*public static final*/ ALERT_SENT /* = new ALERT_SENT() */;
    
    FallDetectionState() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.saferoute.domain.model.FallDetectionState> getEntries() {
        return null;
    }
}