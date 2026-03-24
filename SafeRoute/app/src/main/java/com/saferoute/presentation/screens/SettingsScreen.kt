package com.saferoute.presentation.screens
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.saferoute.domain.model.AppSettings
import com.saferoute.presentation.theme.*
import com.saferoute.presentation.viewmodels.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel
) {
    val settings by viewModel.settings.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Paramètres",
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Protection Section
            SettingsSection(title = "Protection") {
                SettingsSwitchItem(
                    icon = Icons.Default.Warning,
                    title = "Détection de chute",
                    subtitle = "Surveille les mouvements pour détecter les chutes",
                    checked = settings.isFallDetectionEnabled,
                    onCheckedChange = { viewModel.updateFallDetectionEnabled(it) }
                )

                SettingsSwitchItem(
                    icon = Icons.Default.LocationOn,
                    title = "Suivi GPS",
                    subtitle = "Surveille votre position en arrière-plan",
                    checked = settings.isLocationTrackingEnabled,
                    onCheckedChange = { viewModel.updateLocationTrackingEnabled(it) }
                )

                SettingsSwitchItem(
                    icon = Icons.Default.Home,
                    title = "Surveillance des zones",
                    subtitle = "Alerte en cas de sortie anormale d'une zone",
                    checked = settings.isSafeZoneMonitoringEnabled,
                    onCheckedChange = { viewModel.updateSafeZoneMonitoringEnabled(it) }
                )
            }

            // Fall Detection Settings
            if (settings.isFallDetectionEnabled) {
                SettingsSection(title = "Détection de chute") {
                    Text(
                        text = "Sensibilité",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )

                    SingleChoiceSegmentedButtonRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        listOf(
                            AppSettings.SENSITIVITY_LOW to "Faible",
                            AppSettings.SENSITIVITY_MEDIUM to "Moyenne",
                            AppSettings.SENSITIVITY_HIGH to "Élevée"
                        ).forEachIndexed { index, (value, label) ->
                            SegmentedButton(
                                selected = settings.fallDetectionSensitivity == value,
                                onClick = { viewModel.updateFallDetectionSensitivity(value) },
                                shape = SegmentedButtonDefaults.itemShape(
                                    index = index,
                                    count = 3
                                )
                            ) {
                                Text(label)
                            }
                        }
                    }

                    SettingsSwitchItem(
                        icon = Icons.Default.Notifications,
                        title = "Envoi automatique",
                        subtitle = "Envoyer l'alerte si pas de réponse après 30s",
                        checked = settings.autoSendOnNoResponse,
                        onCheckedChange = { viewModel.updateAutoSendOnNoResponse(it) }
                    )
                }
            }

            // SOS Settings
            SettingsSection(title = "Alerte SOS") {
                Text(
                    text = "Compte à rebours: ${settings.sosCountdownSeconds} secondes",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Slider(
                    value = settings.sosCountdownSeconds.toFloat(),
                    onValueChange = { viewModel.updateSOSCountdown(it.toInt()) },
                    valueRange = 3f..10f,
                    steps = 6,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                SettingsSwitchItem(
                    icon = Icons.Default.Place,
                    title = "Inclure la position",
                    subtitle = "Joindre votre position GPS aux alertes",
                    checked = settings.includeLocationInAlerts,
                    onCheckedChange = { viewModel.updateIncludeLocationInAlerts(it) }
                )
            }

            // Security Section
            SettingsSection(title = "Sécurité") {
                SettingsSwitchItem(
                    icon = Icons.Default.Fingerprint,
                    title = "Empreinte digitale",
                    subtitle = "Requiert l'authentification biométrique",
                    checked = settings.isBiometricRequired,
                    onCheckedChange = { viewModel.updateBiometricRequired(it) }
                )
            }

            // Notifications Section
            SettingsSection(title = "Notifications") {
                SettingsSwitchItem(
                    icon = Icons.Default.VolumeUp,
                    title = "Sons",
                    subtitle = "Jouer des sons pour les alertes",
                    checked = settings.soundEnabled,
                    onCheckedChange = { viewModel.updateSoundEnabled(it) }
                )

                SettingsSwitchItem(
                    icon = Icons.Default.Vibration,
                    title = "Vibrations",
                    subtitle = "Vibrer pour les alertes",
                    checked = settings.vibrationEnabled,
                    onCheckedChange = { viewModel.updateVibrationEnabled(it) }
                )
            }

            // Bluetooth Section
            SettingsSection(title = "Communication") {
                SettingsSwitchItem(
                    icon = Icons.Default.Bluetooth,
                    title = "Bluetooth",
                    subtitle = "Fonctionnement hors ligne via Bluetooth",
                    checked = settings.isBluetoothEnabled,
                    onCheckedChange = { viewModel.updateBluetoothEnabled(it) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Reset Button
            OutlinedButton(
                onClick = { viewModel.resetSettings() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = AlertRed
                )
            ) {
                Icon(
                    imageVector = Icons.Default.RestartAlt,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Réinitialiser les paramètres")
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = PrimaryBlue,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column {
                content()
            }
        }
    }
}

@Composable
private fun SettingsSwitchItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Gray500,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = Gray500
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}