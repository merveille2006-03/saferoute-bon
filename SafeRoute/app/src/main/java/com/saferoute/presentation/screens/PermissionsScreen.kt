package com.saferoute.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.isGranted
import com.saferoute.presentation.Screen
import com.saferoute.presentation.theme.*
import androidx.compose.ui.graphics.Color
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsScreen(
    navController: NavController,
    permissionsState: MultiplePermissionsState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Security,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = PrimaryBlue
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Permissions requises",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "SafeRoute a besoin de certaines permissions pour fonctionner correctement et assurer votre sécurité.",
            style = MaterialTheme.typography.bodyLarge,
            color = Gray500,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Permission items
        PermissionItem(
            icon = Icons.Default.LocationOn,
            title = "Localisation",
            description = "Pour suivre votre position et envoyer vos coordonnées en cas d'urgence",
            granted = permissionsState.permissions.find {
                it.permission == android.Manifest.permission.ACCESS_FINE_LOCATION
            }?.status?.isGranted ?: false
        )

        PermissionItem(
            icon = Icons.Default.Sms,
            title = "SMS",
            description = "Pour envoyer des alertes à vos contacts d'urgence",
            granted = permissionsState.permissions.find {
                it.permission == android.Manifest.permission.SEND_SMS
            }?.status?.isGranted ?: false
        )

        PermissionItem(
            icon = Icons.Default.Notifications,
            title = "Notifications",
            description = "Pour vous alerter en cas de détection de danger",
            granted = permissionsState.permissions.find {
                it.permission == android.Manifest.permission.POST_NOTIFICATIONS
            }?.status?.isGranted ?: false
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (permissionsState.allPermissionsGranted) {
            Button(
                onClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Permissions.route) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continuer")
            }
        } else {
            Button(
                onClick = { permissionsState.launchMultiplePermissionRequest() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Accorder les permissions")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Permissions.route) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continuer sans toutes les permissions")
            }
        }
    }
}

@Composable
private fun PermissionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    granted: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (granted) {
                SuccessGreen.copy(alpha = 0.1f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (granted) SuccessGreen else PrimaryBlue,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray500
                )
            }

            Icon(
                imageVector = if (granted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                contentDescription = null,
                tint = if (granted) SuccessGreen else Gray300
            )
        }
    }
}