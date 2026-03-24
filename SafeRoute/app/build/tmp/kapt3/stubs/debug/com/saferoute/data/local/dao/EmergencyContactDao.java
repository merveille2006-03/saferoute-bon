package com.saferoute.data.local.dao;

import androidx.room.*;
import com.saferoute.data.local.entity.EmergencyContactEntity;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00100\u00122\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0015\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u0016\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u0017"}, d2 = {"Lcom/saferoute/data/local/dao/EmergencyContactDao;", "", "deleteAllContacts", "", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteContact", "contact", "Lcom/saferoute/data/local/entity/EmergencyContactEntity;", "(Lcom/saferoute/data/local/entity/EmergencyContactEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteContactById", "contactId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllContacts", "", "getAllContactsFlow", "Lkotlinx/coroutines/flow/Flow;", "getContactById", "getEnabledContacts", "insertContact", "updateContact", "app_debug"})
@androidx.room.Dao()
public abstract interface EmergencyContactDao {
    
    @androidx.room.Query(value = "SELECT * FROM emergency_contacts WHERE userId = :userId ORDER BY priority ASC, createdAt DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllContacts(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.data.local.entity.EmergencyContactEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM emergency_contacts WHERE userId = :userId ORDER BY priority ASC, createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.saferoute.data.local.entity.EmergencyContactEntity>> getAllContactsFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "SELECT * FROM emergency_contacts WHERE id = :contactId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getContactById(long contactId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.data.local.entity.EmergencyContactEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM emergency_contacts WHERE userId = :userId AND isEnabled = 1 ORDER BY priority ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEnabledContacts(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.data.local.entity.EmergencyContactEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertContact(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.entity.EmergencyContactEntity contact, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateContact(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.entity.EmergencyContactEntity contact, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteContact(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.entity.EmergencyContactEntity contact, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM emergency_contacts WHERE id = :contactId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteContactById(long contactId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM emergency_contacts WHERE userId = :userId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllContacts(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}