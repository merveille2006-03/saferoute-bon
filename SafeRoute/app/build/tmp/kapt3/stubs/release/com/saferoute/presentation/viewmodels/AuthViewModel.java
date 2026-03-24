package com.saferoute.presentation.viewmodels;

import com.saferoute.domain.model.AuthResult;
import android.app.Application;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001aB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\u000e\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0012R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lcom/saferoute/presentation/viewmodels/AuthViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_biometricState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/saferoute/presentation/viewmodels/AuthViewModel$BiometricState;", "_isAuthenticated", "", "biometricManager", "Landroidx/biometric/BiometricManager;", "biometricState", "Lkotlinx/coroutines/flow/StateFlow;", "getBiometricState", "()Lkotlinx/coroutines/flow/StateFlow;", "isAuthenticated", "authenticateWithBiometric", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "checkBiometricAvailability", "loginWithPin", "pin", "", "logout", "BiometricState", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AuthViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isAuthenticated = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isAuthenticated = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saferoute.presentation.viewmodels.AuthViewModel.BiometricState> _biometricState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.saferoute.presentation.viewmodels.AuthViewModel.BiometricState> biometricState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.biometric.BiometricManager biometricManager = null;
    
    @javax.inject.Inject()
    public AuthViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isAuthenticated() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.saferoute.presentation.viewmodels.AuthViewModel.BiometricState> getBiometricState() {
        return null;
    }
    
    private final void checkBiometricAvailability() {
    }
    
    public final void authenticateWithBiometric(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentActivity activity) {
    }
    
    public final boolean loginWithPin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin) {
        return false;
    }
    
    public final void logout() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/saferoute/presentation/viewmodels/AuthViewModel$BiometricState;", "", "()V", "Available", "NotAvailable", "NotEnrolled", "Lcom/saferoute/presentation/viewmodels/AuthViewModel$BiometricState$Available;", "Lcom/saferoute/presentation/viewmodels/AuthViewModel$BiometricState$NotAvailable;", "Lcom/saferoute/presentation/viewmodels/AuthViewModel$BiometricState$NotEnrolled;", "app_release"})
    public static abstract class BiometricState {
        
        private BiometricState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/presentation/viewmodels/AuthViewModel$BiometricState$Available;", "Lcom/saferoute/presentation/viewmodels/AuthViewModel$BiometricState;", "()V", "app_release"})
        public static final class Available extends com.saferoute.presentation.viewmodels.AuthViewModel.BiometricState {
            @org.jetbrains.annotations.NotNull()
            public static final com.saferoute.presentation.viewmodels.AuthViewModel.BiometricState.Available INSTANCE = null;
            
            private Available() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/presentation/viewmodels/AuthViewModel$BiometricState$NotAvailable;", "Lcom/saferoute/presentation/viewmodels/AuthViewModel$BiometricState;", "()V", "app_release"})
        public static final class NotAvailable extends com.saferoute.presentation.viewmodels.AuthViewModel.BiometricState {
            @org.jetbrains.annotations.NotNull()
            public static final com.saferoute.presentation.viewmodels.AuthViewModel.BiometricState.NotAvailable INSTANCE = null;
            
            private NotAvailable() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/presentation/viewmodels/AuthViewModel$BiometricState$NotEnrolled;", "Lcom/saferoute/presentation/viewmodels/AuthViewModel$BiometricState;", "()V", "app_release"})
        public static final class NotEnrolled extends com.saferoute.presentation.viewmodels.AuthViewModel.BiometricState {
            @org.jetbrains.annotations.NotNull()
            public static final com.saferoute.presentation.viewmodels.AuthViewModel.BiometricState.NotEnrolled INSTANCE = null;
            
            private NotEnrolled() {
            }
        }
    }
}