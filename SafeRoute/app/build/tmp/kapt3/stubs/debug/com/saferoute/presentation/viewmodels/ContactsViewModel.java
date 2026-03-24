package com.saferoute.presentation.viewmodels;

import androidx.lifecycle.ViewModel;
import com.saferoute.domain.model.EmergencyContact;
import com.saferoute.domain.repository.EmergencyContactRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001a\u001a\u00020\nJ\u0006\u0010\u001b\u001a\u00020\u0016J\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\bJ\b\u0010\u001e\u001a\u00020\u0016H\u0002J\u0016\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!J\u000e\u0010#\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\bJ\u000e\u0010$\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u000e\u0010\u0014\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/saferoute/presentation/viewmodels/ContactsViewModel;", "Landroidx/lifecycle/ViewModel;", "contactRepository", "Lcom/saferoute/domain/repository/EmergencyContactRepository;", "(Lcom/saferoute/domain/repository/EmergencyContactRepository;)V", "_contacts", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/saferoute/domain/model/EmergencyContact;", "_errorMessage", "", "_isLoading", "", "contacts", "Lkotlinx/coroutines/flow/StateFlow;", "getContacts", "()Lkotlinx/coroutines/flow/StateFlow;", "errorMessage", "getErrorMessage", "isLoading", "userId", "addContact", "", "name", "phoneNumber", "email", "relationship", "clearError", "deleteContact", "contact", "loadContacts", "reorderContacts", "fromIndex", "", "toIndex", "toggleContactEnabled", "updateContact", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ContactsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.saferoute.domain.repository.EmergencyContactRepository contactRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = "default_user";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.saferoute.domain.model.EmergencyContact>> _contacts = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.saferoute.domain.model.EmergencyContact>> contacts = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> errorMessage = null;
    
    @javax.inject.Inject()
    public ContactsViewModel(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.repository.EmergencyContactRepository contactRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.saferoute.domain.model.EmergencyContact>> getContacts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getErrorMessage() {
        return null;
    }
    
    private final void loadContacts() {
    }
    
    public final void addContact(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.Nullable()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String relationship) {
    }
    
    public final void updateContact(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.EmergencyContact contact) {
    }
    
    public final void deleteContact(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.EmergencyContact contact) {
    }
    
    public final void toggleContactEnabled(@org.jetbrains.annotations.NotNull()
    com.saferoute.domain.model.EmergencyContact contact) {
    }
    
    public final void reorderContacts(int fromIndex, int toIndex) {
    }
    
    public final void clearError() {
    }
}