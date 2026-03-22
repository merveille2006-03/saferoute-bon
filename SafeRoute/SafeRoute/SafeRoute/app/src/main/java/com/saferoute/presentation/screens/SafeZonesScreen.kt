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
import com.saferoute.domain.model.SafeZone
import com.saferoute.presentation.theme.*
import com.saferoute.presentation.viewmodels.SafeZonesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SafeZonesScreen(
    navController: NavController,
    viewModel: SafeZonesViewModel
) {
    val safeZones by viewModel.safeZones.collectAsState()
    val currentLocation by viewModel.currentLocation.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Zones sécurisées",
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
                    IconButton(onClick = { viewModel.refreshLocation() }) {
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = SafeZoneGreen
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Ajouter une zone",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (safeZones.isEmpty() && !isLoading) {
                EmptySafeZonesState()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(safeZones, key = { it.id }) { zone ->
                        val isInside = currentLocation?.let {
                            viewModel.isLocationInSafeZone(it)?.id == zone.id
                        } ?: false

                        SafeZoneCard(
                            zone = zone,
                            isInside = isInside,
                            onToggleActive = { viewModel.toggleSafeZoneActive(zone) },
                            onDelete = { viewModel.deleteSafeZone(zone) }
                        )
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

    // Add Safe Zone Dialog
    if (showAddDialog) {
        AddSafeZoneDialog(
            currentLocation = currentLocation,
            onDismiss = { showAddDialog = false },
            onAdd = { name, radius, type ->
                viewModel.addSafeZoneAtCurrentLocation(name, radius, type)
                showAddDialog = false
            }
        )
    }
}

@Composable
private fun EmptySafeZonesState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Map,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = Gray300
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Aucune zone sécurisée",
            style = MaterialTheme.typography.titleLarge,
            color = Gray500
        )

        Text(
            text = "Définissez des zones comme votre domicile ou votre travail pour être alerté en cas de sortie anormale",
            style = MaterialTheme.typography.bodyMedium,
            color = Gray400,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SafeZoneCard(
    zone: SafeZone,
    isInside: Boolean,
    onToggleActive: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (zone.isActive) {
                SafeZoneGreen.copy(alpha = 0.05f)
            } else {
                Gray100
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Zone type icon
            Surface(
                shape = MaterialTheme.shapes.small,
                color = when (zone.type) {
                    SafeZone.TYPE_HOME -> PrimaryBlue
                    SafeZone.TYPE_WORK -> SecondaryTeal
                    SafeZone.TYPE_SCHOOL -> WarningYellow
                    else -> Gray400
                },
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = when (zone.type) {
                            SafeZone.TYPE_HOME -> Icons.Default.Home
                            SafeZone.TYPE_WORK -> Icons.Default.Work
                            SafeZone.TYPE_SCHOOL -> Icons.Default.School
                            else -> Icons.Default.Place
                        },
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = zone.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    if (isInside) {
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            color = SafeZoneGreen,
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text(
                                text = "À l'intérieur",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Text(
                    text = "Rayon: ${zone.radius.toInt()}m",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray500
                )

                Text(
                    text = zone.type.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.labelSmall,
                    color = Gray400
                )
            }

            Switch(
                checked = zone.isActive,
                onCheckedChange = { onToggleActive() }
            )

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Supprimer",
                    tint = AlertRed
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddSafeZoneDialog(
    currentLocation: com.saferoute.domain.model.LocationData?,
    onDismiss: () -> Unit,
    onAdd: (String, Double, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var radius by remember { mutableStateOf("200") }
    var type by remember { mutableStateOf(SafeZone.TYPE_HOME) }

    val zoneTypes = listOf(
        SafeZone.TYPE_HOME to "Domicile",
        SafeZone.TYPE_WORK to "Travail",
        SafeZone.TYPE_SCHOOL to "École",
        SafeZone.TYPE_OTHER to "Autre"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Ajouter une zone") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (currentLocation == null) {
                    Surface(
                        color = WarningYellow.copy(alpha = 0.1f),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = "Position actuelle non disponible. Activez le GPS.",
                            style = MaterialTheme.typography.bodySmall,
                            color = WarningYellow,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nom de la zone") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = radius,
                    onValueChange = { radius = it.filter { char -> char.isDigit() } },
                    label = { Text("Rayon (mètres)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Type de zone",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )

                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    zoneTypes.forEachIndexed { index, (value, label) ->
                        SegmentedButton(
                            selected = type == value,
                            onClick = { type = value },
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = zoneTypes.size
                            )
                        ) {
                            Text(label, style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val radiusValue = radius.toDoubleOrNull() ?: SafeZone.DEFAULT_RADIUS
                    if (name.isNotBlank() && currentLocation != null) {
                        onAdd(name, radiusValue, type)
                    }
                },
                enabled = name.isNotBlank() && currentLocation != null
            ) {
                Text("Ajouter")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Annuler")
            }
        }
    )
}