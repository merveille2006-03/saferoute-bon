package com.saferoute.presentation;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import com.google.accompanist.permissions.ExperimentalPermissionsApi;
import com.saferoute.presentation.screens.*;
import com.saferoute.presentation.viewmodels.*;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\b\u0007\b\t\n\u000b\f\r\u000eB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\b\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/saferoute/presentation/Screen;", "", "route", "", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "AlertHistory", "Contacts", "FallHistory", "Home", "Login", "Permissions", "SafeZones", "Settings", "Lcom/saferoute/presentation/Screen$AlertHistory;", "Lcom/saferoute/presentation/Screen$Contacts;", "Lcom/saferoute/presentation/Screen$FallHistory;", "Lcom/saferoute/presentation/Screen$Home;", "Lcom/saferoute/presentation/Screen$Login;", "Lcom/saferoute/presentation/Screen$Permissions;", "Lcom/saferoute/presentation/Screen$SafeZones;", "Lcom/saferoute/presentation/Screen$Settings;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    
    private Screen(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/presentation/Screen$AlertHistory;", "Lcom/saferoute/presentation/Screen;", "()V", "app_debug"})
    public static final class AlertHistory extends com.saferoute.presentation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.saferoute.presentation.Screen.AlertHistory INSTANCE = null;
        
        private AlertHistory() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/presentation/Screen$Contacts;", "Lcom/saferoute/presentation/Screen;", "()V", "app_debug"})
    public static final class Contacts extends com.saferoute.presentation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.saferoute.presentation.Screen.Contacts INSTANCE = null;
        
        private Contacts() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/presentation/Screen$FallHistory;", "Lcom/saferoute/presentation/Screen;", "()V", "app_debug"})
    public static final class FallHistory extends com.saferoute.presentation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.saferoute.presentation.Screen.FallHistory INSTANCE = null;
        
        private FallHistory() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/presentation/Screen$Home;", "Lcom/saferoute/presentation/Screen;", "()V", "app_debug"})
    public static final class Home extends com.saferoute.presentation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.saferoute.presentation.Screen.Home INSTANCE = null;
        
        private Home() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/presentation/Screen$Login;", "Lcom/saferoute/presentation/Screen;", "()V", "app_debug"})
    public static final class Login extends com.saferoute.presentation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.saferoute.presentation.Screen.Login INSTANCE = null;
        
        private Login() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/presentation/Screen$Permissions;", "Lcom/saferoute/presentation/Screen;", "()V", "app_debug"})
    public static final class Permissions extends com.saferoute.presentation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.saferoute.presentation.Screen.Permissions INSTANCE = null;
        
        private Permissions() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/presentation/Screen$SafeZones;", "Lcom/saferoute/presentation/Screen;", "()V", "app_debug"})
    public static final class SafeZones extends com.saferoute.presentation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.saferoute.presentation.Screen.SafeZones INSTANCE = null;
        
        private SafeZones() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/saferoute/presentation/Screen$Settings;", "Lcom/saferoute/presentation/Screen;", "()V", "app_debug"})
    public static final class Settings extends com.saferoute.presentation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.saferoute.presentation.Screen.Settings INSTANCE = null;
        
        private Settings() {
        }
    }
}