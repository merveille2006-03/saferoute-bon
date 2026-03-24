package com.saferoute.data.repository;

import com.saferoute.data.local.dao.EmergencyContactDao;
import com.saferoute.data.local.entity.EmergencyContactEntity;
import com.saferoute.domain.model.EmergencyContact;
import com.saferoute.domain.repository.EmergencyContactRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00100\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010\u0018\u001a\u00020\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u0010H\u0096@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/saferoute/data/repository/EmergencyContactRepositoryImpl;", "Lcom/saferoute/domain/repository/EmergencyContactRepository;", "contactDao", "Lcom/saferoute/data/local/dao/EmergencyContactDao;", "(Lcom/saferoute/data/local/dao/EmergencyContactDao;)V", "addContact", "", "contact", "Lcom/saferoute/domain/model/EmergencyContact;", "(Lcom/saferoute/domain/model/EmergencyContact;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteContact", "", "deleteContactById", "contactId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllContacts", "", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllContactsFlow", "Lkotlinx/coroutines/flow/Flow;", "getContactById", "getEnabledContacts", "reorderContacts", "contacts", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateContact", "app_debug"})
public final class EmergencyContactRepositoryImpl implements com.saferoute.domain.repository.EmergencyContactRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.data.local.dao.EmergencyContactDao contactDao = null;
    
    @javax.inject.Inject()
    public EmergencyContactRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.saferoute.data.local.dao.EmergencyContactDao contactDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllContacts(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.domain.model.EmergencyContact>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.saferoute.domain.model.EmergencyContact>> getAllContactsFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getContactById(long contactId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.saferoute.domain.model.EmergencyContact> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addContact(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.EmergencyContact contact, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateContact(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.EmergencyContact contact, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteContact(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.EmergencyContact contact, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteContactById(long contactId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getEnabledContacts(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.saferoute.domain.model.EmergencyContact>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object reorderContacts(@org.jetbrains.annotations.NotNull()
    java.util.List<com.saferoute.domain.model.EmergencyContact> contacts, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}