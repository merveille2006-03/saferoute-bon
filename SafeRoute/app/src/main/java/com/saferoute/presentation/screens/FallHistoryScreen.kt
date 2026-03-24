package com.saferoute.presentation.screens

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.saferoute.domain.model.FallEvent
import com.saferoute.presentation.theme.*
import com.saferoute.presentation.viewmodels.FallHistoryViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FallHistoryScreen(
    navController: NavController,
    viewModel: FallHistoryViewModel
) {
    val fallEvents by viewModel.fallEvents.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Historique des chutes",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.refreshHistory() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Rafraîchir"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (fallEvents.isEmpty() && !isLoading) {
                EmptyFallHistoryState()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(fallEvents, key = { it.id }) { event ->
                        FallEventCard(event = event)
                    }
                }
            }

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun EmptyFallHistoryState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.HealthAndSafety,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = SuccessGreen
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Aucune chute détectée",
            style = MaterialTheme.typography.titleLarge,
            color = SuccessGreen
        )

        Text(
            text = "C'est une bonne nouvelle! Aucune chute n'a été enregistrée.",
            style = MaterialTheme.typography.bodyMedium,
            color = Gray400,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun FallEventCard(event: FallEvent) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE)

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = when (event.isConfirmed) {
                true -> AlertRed.copy(alpha = 0.05f)
                false -> SuccessGreen.copy(alpha = 0.05f)
                null -> WarningYellow.copy(alpha = 0.05f)
            }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = when (event.isConfirmed) {
                        true -> Icons.Default.Warning
                        false -> Icons.Default.CheckCircle
                        null -> Icons.Default.Help
                    },
                    contentDescription = null,
                    tint = when (event.isConfirmed) {
                        true -> AlertRed
                        false -> SuccessGreen
                        null -> WarningYellow
                    },
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = dateFormat.format(Date(event.timestamp)),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = when (event.isConfirmed) {
                            true -> "Chute confirmée"
                            false -> "Fausse alerte"
                            null -> "En attente de confirmation"
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = when (event.isConfirmed) {
                            true -> AlertRed
                            false -> SuccessGreen
                            null -> WarningYellow
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(
                    label = "Force d'impact",
                    value = "%.1f G".format(event.impactForce)
                )

                StatItem(
                    label = "Durée",
                    value = "${event.fallDuration}ms"
                )

                event.responseTime?.let {
                    StatItem(
                        label = "Temps de réponse",
                        value = "${it / 1000}s"
                    )
                }
            }

            if (event.alertSent) {
                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = AlertRed.copy(alpha = 0.1f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.NotificationImportant,
                            contentDescription = null,
                            tint = AlertRed,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Alerte envoyée",
                            style = MaterialTheme.typography.labelSmall,
                            color = AlertRed
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = PrimaryBlue
        )

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Gray500
        )
    }
}