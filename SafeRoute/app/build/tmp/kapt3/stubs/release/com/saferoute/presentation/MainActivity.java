package com.saferoute.presentation;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import com.google.accompanist.permissions.ExperimentalPermissionsApi;
import com.saferoute.presentation.screens.*;
import com.saferoute.presentation.viewmodels.*;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0012"}, d2 = {"Lcom/saferoute/presentation/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "authViewModel", "Lcom/saferoute/presentation/viewmodels/AuthViewModel;", "getAuthViewModel", "()Lcom/saferoute/presentation/viewmodels/AuthViewModel;", "authViewModel$delegate", "Lkotlin/Lazy;", "mainViewModel", "Lcom/saferoute/presentation/viewmodels/MainViewModel;", "getMainViewModel", "()Lcom/saferoute/presentation/viewmodels/MainViewModel;", "mainViewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_release"})
public final class MainActivity extends androidx.activity.ComponentActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy mainViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy authViewModel$delegate = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.saferoute.presentation.viewmodels.MainViewModel getMainViewModel() {
        return null;
    }
    
    private final com.saferoute.presentation.viewmodels.AuthViewModel getAuthViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class})
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
}