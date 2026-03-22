package com.saferoute.presentation.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.saferoute.domain.model.FallDetectionState
import com.saferoute.presentation.Screen
import com.saferoute.presentation.components.SOSButton
import com.saferoute.presentation.components.StatusCard
import com.saferoute.presentation.theme.*
import com.saferoute.presentation.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val currentLocation by viewModel.currentLocation.collectAsState()
    val fallDetectionState by viewModel.fallDetectionState.collectAsState()
    val isServiceRunning by viewModel.isServiceRunning.collectAsState()
    val safeZones by viewModel.safeZones.collectAsState()

    var showSOSDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "SafeRoute",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Paramètres",
                            tint = Gray600
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Status Section
            StatusSection(
                isServiceRunning = isServiceRunning,
                location = currentLocation,
                safeZoneCount = safeZones.size
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Fall Detection Alert
            AnimatedVisibility(
                visible = fallDetectionState == FallDetectionState.CONFIRMATION_REQUIRED,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                FallAlertCard(
                    onConfirm = { viewModel.confirmFall(true) },
                    onCancel = { viewModel.confirmFall(false) }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // SOS Button
            SOSButton(
                onClick = { showSOSDialog = true },
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Appuyez pour alerter",
                style = MaterialTheme.typography.bodyMedium,
                color = Gray500
            )

            Spacer(modifier = Modifier.weight(1f))

            // Quick Actions
            QuickActionsRow(navController)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // SOS Confirmation Dialog
    if (showSOSDialog) {
        SOSConfirmationDialog(
            onConfirm = {
                viewModel.sendSOSAlert()
                showSOSDialog = false
            },
            onDismiss = { showSOSDialog = false }
        )
    }
}

@Composable
private fun StatusSection(
    isServiceRunning: Boolean,
    location: com.saferoute.domain.model.LocationData?,
    safeZoneCount: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "État du système",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatusCard(
                icon = Icons.Default.Security,
                title = "Protection",
                subtitle = if (isServiceRunning) "Active" else "Inactive",
                isActive = isServiceRunning,
                modifier = Modifier.weight(1f)
            )

            StatusCard(
                icon = Icons.Default.LocationOn,
                title = "GPS",
                subtitle = if (location != null) "OK" else "En attente",
                isActive = location != null,
                modifier = Modifier.weight(1f)
            )

            StatusCard(
                icon = Icons.Default.Home,
                title = "Zones",
                subtitle = "$safeZoneCount",
                isActive = safeZoneCount > 0,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun FallAlertCard(
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = AlertRed.copy(alpha = 0.1f)
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            width = 2.dp,
            brush = Brush.linearGradient(listOf(AlertRed, AlertOrange))
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = AlertRed,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Chute détectée!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = AlertRed
            )

            Text(
                text = "Êtes-vous OK? Répondez dans 30 secondes.",
                style = MaterialTheme.typography.bodyMedium,
                color = Gray600,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Je vais bien")
                }

                Button(
                    onClick = onConfirm,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AlertRed
                    )
                ) {
                    Text("Envoyer alerte")
                }
            }
        }
    }
}

@Composable
private fun SOSConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = AlertRed
            )
        },
        title = {
            Text("Confirmer l'alerte SOS")
        },
        text = {
            Text("Cette action enverra immédiatement une alerte à tous vos contacts d'urgence avec votre position.")
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = AlertRed
                )
            ) {
                Text("Envoyer l'alerte")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Annuler")
            }
        }
    )
}

@Composable
private fun QuickActionsRow(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        QuickActionButton(
            icon = Icons.Default.Contacts,
            label = "Contacts",
            onClick = { navController.navigate(Screen.Contacts.route) }
        )

        QuickActionButton(
            icon = Icons.Default.Map,
            label = "Zones",
            onClick = { navController.navigate(Screen.SafeZones.route) }
        )

        QuickActionButton(
            icon = Icons.Default.History,
            label = "Historique",
            onClick = { navController.navigate(Screen.FallHistory.route) }
        )
    }
}

@Composable
private fun QuickActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilledIconButton(
            onClick = onClick,
            modifier = Modifier.size(56.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = PrimaryBlue.copy(alpha = 0.1f),
                contentColor = PrimaryBlue
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Gray600
        )
    }
}