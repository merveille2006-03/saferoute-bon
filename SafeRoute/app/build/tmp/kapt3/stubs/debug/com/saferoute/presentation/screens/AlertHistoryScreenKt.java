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
import com.saferoute.domain.model.SOSAlert;
import com.saferoute.presentation.theme.*;
import com.saferoute.presentation.viewmodels.AlertHistoryViewModel;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001a\u0018\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a\b\u0010\t\u001a\u00020\u0001H\u0003\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0003\u00a8\u0006\r"}, d2 = {"AlertCard", "", "alert", "Lcom/saferoute/domain/model/SOSAlert;", "AlertHistoryScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/saferoute/presentation/viewmodels/AlertHistoryViewModel;", "EmptyAlertHistoryState", "StatsCard", "stats", "Lcom/saferoute/presentation/viewmodels/AlertHistoryViewModel$AlertStats;", "app_debug"})
@kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
public final class AlertHistoryScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void AlertHistoryScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.saferoute.presentation.viewmodels.AlertHistoryViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatsCard(com.saferoute.presentation.viewmodels.AlertHistoryViewModel.AlertStats stats) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyAlertHistoryState() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AlertCard(com.saferoute.domain.model.SOSAlert alert) {
    }
}