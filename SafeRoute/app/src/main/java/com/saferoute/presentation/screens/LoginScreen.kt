package com.saferoute.presentation.screens

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.saferoute.R
import com.saferoute.presentation.theme.*
import com.saferoute.presentation.viewmodels.AuthViewModel
import com.saferoute.presentation.navigation.Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    val context = LocalContext.current
    val biometricState by viewModel.biometricState.collectAsState()

    var pin by remember { mutableStateOf("") }
    var showPinError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Box(
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            // Using a placeholder - replace with actual app logo
            Icon(
                imageVector = Icons.Default.Fingerprint,
                contentDescription = "SafeRoute Logo",
                modifier = Modifier.size(80.dp),
                tint = PrimaryBlue
            )
        }

        Text(
            text = "SafeRoute",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = PrimaryBlue
        )

        Text(
            text = "Votre sécurité, notre priorité",
            style = MaterialTheme.typography.bodyLarge,
            color = Gray500,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        // PIN Input
        OutlinedTextField(
            value = pin,
            onValueChange = {
                pin = it.take(4)
                showPinError = false
            },
            label = { Text("Code PIN") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            isError = showPinError,
            supportingText = if (showPinError) {
                { Text("Code PIN incorrect") }
            } else null,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (viewModel.loginWithPin(pin)) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) {
                            this.inclusive = true
                        }
                    }
                } else {
                    showPinError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Se connecter")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Biometric option
        if (biometricState is AuthViewModel.BiometricState.Available) {
            OutlinedButton(
                onClick = {
                    (context as? FragmentActivity)?.let { activity ->
                        viewModel.authenticateWithBiometric(activity)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Fingerprint,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Empreinte digitale")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "PIN par défaut: 1234",
            style = MaterialTheme.typography.bodySmall,
            color = Gray400,
            textAlign = TextAlign.Center
        )
    }
}