@file:OptIn(ExperimentalMaterial3Api::class)
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
import com.saferoute.domain.model.SOSAlert
import com.saferoute.presentation.theme.*
import com.saferoute.presentation.viewmodels.AlertHistoryViewModel
import java.text.SimpleDateFormat
import java.util.*



data class StatItem(
    val title: String,
    val value: Int
)

@Composable
fun AlertHistoryScreen(
    navController: NavController,
    viewModel: AlertHistoryViewModel
) {
    val alerts by viewModel.alerts.collectAsState()
    val stats by viewModel.stats.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Historique des alertes",
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
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Stats Card
                StatsCard(stats = stats)

                // Alerts List
                if (alerts.isEmpty() && !isLoading) {
                    EmptyAlertHistoryState()
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(alerts, key = { it.id }) { alert ->
                            AlertCard(alert = alert)
                        }
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
private fun StatsCard(stats: AlertHistoryViewModel.AlertStats) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryBlue.copy(alpha = 0.05f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Statistiques",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(
                    title = "Total",
                    value = stats.totalAlerts
                )

                StatItem(
                    title = "SOS",
                    value = stats.manualAlerts
                )

                StatItem(
                    title = "Chutes",
                    value = stats.fallAlerts
                )

                StatItem(
                    title = "Zones",
                    value = stats.safeZoneAlerts
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = stats.successRate / 100f,
                modifier = Modifier.fillMaxWidth(),
                color = SuccessGreen,
                trackColor = Gray200
            )

            Text(
                text = "Taux de succès: ${stats.successRate}%",
                style = MaterialTheme.typography.labelSmall,
                color = Gray500,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun EmptyAlertHistoryState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.NotificationsNone,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = Gray300
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Aucune alerte",
            style = MaterialTheme.typography.titleLarge,
            color = Gray500
        )

        Text(
            text = "Aucune alerte n'a été envoyée pour le moment.",
            style = MaterialTheme.typography.bodyMedium,
            color = Gray400,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun AlertCard(alert: SOSAlert) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE)

    Card(
        modifier = Modifier.fillMaxWidth()
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
                // Trigger type icon
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = when (alert.triggerType) {
                        SOSAlert.TRIGGER_MANUAL -> AlertRed
                        SOSAlert.TRIGGER_FALL_DETECTION -> WarningYellow
                        SOSAlert.TRIGGER_SAFE_ZONE_EXIT -> PrimaryBlue
                        else -> Gray400
                    },
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = when (alert.triggerType) {
                                SOSAlert.TRIGGER_MANUAL -> Icons.Default.Sos
                                SOSAlert.TRIGGER_FALL_DETECTION -> Icons.Default.Warning
                                SOSAlert.TRIGGER_SAFE_ZONE_EXIT -> Icons.Default.Map
                                else -> Icons.Default.Notifications
                            },
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = when (alert.triggerType) {
                            SOSAlert.TRIGGER_MANUAL -> "Alerte SOS"
                            SOSAlert.TRIGGER_FALL_DETECTION -> "Détection de chute"
                            SOSAlert.TRIGGER_SAFE_ZONE_EXIT -> "Sortie de zone"
                            else -> "Alerte"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = dateFormat.format(Date(alert.timestamp)),
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray500
                    )
                }

                // Status indicator
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = when (alert.status) {
                        SOSAlert.STATUS_DELIVERED -> SuccessGreen
                        SOSAlert.STATUS_SENT -> PrimaryBlue
                        SOSAlert.STATUS_FAILED -> AlertRed
                        else -> WarningYellow
                    }
                ) {
                    Text(
                        text = when (alert.status) {
                            SOSAlert.STATUS_DELIVERED -> "Livré"
                            SOSAlert.STATUS_SENT -> "Envoyé"
                            SOSAlert.STATUS_FAILED -> "Échec"
                            else -> "En attente"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Recipients info
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Contacts,
                    contentDescription = null,
                    tint = Gray400,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${alert.recipients.size} destinataire(s)",
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray500
                )

                if (alert.deliveredCount > 0) {
                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = SuccessGreen,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "${alert.deliveredCount} livré(s)",
                        style = MaterialTheme.typography.bodySmall,
                        color = SuccessGreen
                    )
                }
            }

            // Location info
            alert.location?.let { location ->
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Gray400,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "%.6f, %.6f".format(location.latitude, location.longitude),
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray500
                    )
                }
            }
        }
    }
}