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
import com.saferoute.domain.model.FallEvent;
import com.saferoute.presentation.theme.*;
import com.saferoute.presentation.viewmodels.FallHistoryViewModel;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0003\u001a\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0003\u001a\u0018\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0003\u00a8\u0006\u000e"}, d2 = {"EmptyFallHistoryState", "", "FallEventCard", "event", "Lcom/saferoute/domain/model/FallEvent;", "FallHistoryScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/saferoute/presentation/viewmodels/FallHistoryViewModel;", "StatItem", "label", "", "value", "app_release"})
public final class FallHistoryScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void FallHistoryScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.saferoute.presentation.viewmodels.FallHistoryViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyFallHistoryState() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FallEventCard(com.saferoute.domain.model.FallEvent event) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatItem(java.lang.String label, java.lang.String value) {
    }
}