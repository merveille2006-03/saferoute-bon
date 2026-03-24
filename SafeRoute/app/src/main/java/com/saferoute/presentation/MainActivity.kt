package com.saferoute.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.saferoute.presentation.screens.*
import com.saferoute.presentation.theme.SafeRouteTheme
import com.saferoute.presentation.viewmodels.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { mainViewModel.isLoading.value }

        setContent {
            SafeRouteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()

                    // Permission handling
                    val permissionsState = rememberMultiplePermissionsState(
                        permissions = listOf(
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.SEND_SMS,
                            android.Manifest.permission.POST_NOTIFICATIONS
                        )
                    )

                    LaunchedEffect(Unit) {
                        permissionsState.launchMultiplePermissionRequest()
                    }

                    val startDestination = when {
                        !isAuthenticated -> Screen.Login.route
                        !permissionsState.allPermissionsGranted -> Screen.Permissions.route
                        else -> Screen.Home.route
                    }

                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {
                        composable(Screen.Login.route) {
                            LoginScreen(
                                navController = navController,
                                viewModel = authViewModel
                            )
                        }

                        composable(Screen.Permissions.route) {
                            PermissionsScreen(
                                navController = navController,
                                permissionsState = permissionsState
                            )
                        }

                        composable(Screen.Home.route) {
                            HomeScreen(
                                navController = navController,
                                viewModel = mainViewModel
                            )
                        }

                        composable(Screen.Contacts.route) {
                            ContactsScreen(
                                navController = navController,
                                viewModel = viewModels<ContactsViewModel>().value
                            )
                        }

                        composable(Screen.SafeZones.route) {
                            SafeZonesScreen(
                                navController = navController,
                                viewModel = viewModels<SafeZonesViewModel>().value
                            )
                        }

                        composable(Screen.Settings.route) {
                            SettingsScreen(
                                navController = navController,
                                viewModel = viewModels<SettingsViewModel>().value
                            )
                        }

                        composable(Screen.FallHistory.route) {
                            FallHistoryScreen(
                                navController = navController,
                                viewModel = viewModels<FallHistoryViewModel>().value
                            )
                        }

                        composable(Screen.AlertHistory.route) {
                            AlertHistoryScreen(
                                navController = navController,
                                viewModel = viewModels<AlertHistoryViewModel>().value
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Permissions : Screen("permissions")
    object Home : Screen("home")
    object Contacts : Screen("contacts")
    object SafeZones : Screen("safe_zones")
    object Settings : Screen("settings")
    object FallHistory : Screen("fall_history")
    object AlertHistory : Screen("alert_history")
}