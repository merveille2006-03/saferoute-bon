package com.saferoute.presentation.screens;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavController;
import com.saferoute.domain.model.SafeZone;
import com.saferoute.presentation.theme.*;
import com.saferoute.presentation.viewmodels.SafeZonesViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a@\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u001e\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u001a\b\u0010\n\u001a\u00020\u0001H\u0003\u001a4\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u0018\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007\u00a8\u0006\u0017"}, d2 = {"AddSafeZoneDialog", "", "currentLocation", "Lcom/saferoute/domain/model/LocationData;", "onDismiss", "Lkotlin/Function0;", "onAdd", "Lkotlin/Function3;", "", "", "EmptySafeZonesState", "SafeZoneCard", "zone", "Lcom/saferoute/domain/model/SafeZone;", "isInside", "", "onToggleActive", "onDelete", "SafeZonesScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/saferoute/presentation/viewmodels/SafeZonesViewModel;", "app_release"})
public final class SafeZonesScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SafeZonesScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.saferoute.presentation.viewmodels.SafeZonesViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptySafeZonesState() {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void SafeZoneCard(com.saferoute.domain.model.SafeZone zone, boolean isInside, kotlin.jvm.functions.Function0<kotlin.Unit> onToggleActive, kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void AddSafeZoneDialog(com.saferoute.domain.model.LocationData currentLocation, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.Double, ? super java.lang.String, kotlin.Unit> onAdd) {
    }
}