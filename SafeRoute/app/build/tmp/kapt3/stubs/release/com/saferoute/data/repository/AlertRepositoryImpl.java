package com.saferoute.data.repository;

import android.content.Context;
import android.telephony.SmsManager;
import com.saferoute.data.local.dao.SOSAlertDao;
import com.saferoute.data.local.entity.SOSAlertEntity;
import com.saferoute.domain.model.LocationData;
import com.saferoute.domain.model.SOSAlert;
import com.saferoute.domain.repository.AlertRepository;
import com.saferoute.domain.repository.EmergencyContactRepository;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f0\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J:\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001b\u0010\u001cJ2\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u001f\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b \u0010!J@\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\u00162\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020\u00112\b\u0010&\u001a\u0004\u0018\u00010\u0011H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\'\u0010(J\u000e\u0010)\u001a\u00020*H\u0096@\u00a2\u0006\u0002\u0010+J\u001e\u0010,\u001a\u00020-2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010.\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010/R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00060"}, d2 = {"Lcom/saferoute/data/repository/AlertRepositoryImpl;", "Lcom/saferoute/domain/repository/AlertRepository;", "context", "Landroid/content/Context;", "sosAlertDao", "Lcom/saferoute/data/local/dao/SOSAlertDao;", "contactRepository", "Lcom/saferoute/domain/repository/EmergencyContactRepository;", "(Landroid/content/Context;Lcom/saferoute/data/local/dao/SOSAlertDao;Lcom/saferoute/domain/repository/EmergencyContactRepository;)V", "getAlertById", "Lcom/saferoute/domain/model/SOSAlert;", "alertId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlertHistory", "", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlertHistoryFlow", "Lkotlinx/coroutines/flow/Flow;", "sendEmailAlert", "Lkotlin/Result;", "", "emails", "subject", "body", "sendEmailAlert-BWLJW6A", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSMSAlert", "phoneNumbers", "message", "sendSMSAlert-0E7RQCE", "(Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSOSAlert", "location", "Lcom/saferoute/domain/model/LocationData;", "triggerType", "customMessage", "sendSOSAlert-yxL6bBk", "(Ljava/lang/String;Lcom/saferoute/domain/model/LocationData;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "testAlertSystem", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAlertStatus", "", "status", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class AlertRepositoryImpl implements com.saferoute.domain.repository.AlertRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.data.local.dao.SOSAlertDao sosAlertDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.domain.repository.EmergencyContactRepository contactRepository = null;
    
    @javax.inject.Inject()
    public AlertRepositoryImpl(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.dao.SOSAlertDao sosAlertDao, @org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.EmergencyContactRepository contactRepository) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAlertHistory(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.domain.model.SOSAlert>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.saferoute.domain.model.SOSAlert>> getAlertHistoryFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAlertById(long alertId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.domain.model.SOSAlert> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateAlertStatus(long alertId, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object testAlertSystem(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}