package com.saferoute.presentation.theme
import androidx.compose.ui.graphics.Color
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlueLight,
    onPrimary = Gray900,
    primaryContainer = PrimaryBlueDark,
    onPrimaryContainer = Gray50,
    secondary = SecondaryTeal,
    onSecondary = Gray900,
    secondaryContainer = SecondaryTealDark,
    onSecondaryContainer = Gray50,
    tertiary = AlertOrange,
    onTertiary = Gray900,
    tertiaryContainer = AlertRedDark,
    onTertiaryContainer = Gray50,
    error = AlertRed,
    onError = Gray50,
    errorContainer = AlertRedDark,
    onErrorContainer = Gray50,
    background = BackgroundDark,
    onBackground = Gray50,
    surface = SurfaceDark,
    onSurface = Gray50,
    surfaceVariant = Gray700,
    onSurfaceVariant = Gray300,
    outline = Gray500
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = Color.White,
    primaryContainer = PrimaryBlueLight,
    onPrimaryContainer = Gray900,
    secondary = SecondaryTeal,
    onSecondary = Color.White,
    secondaryContainer = SecondaryTeal.copy(alpha = 0.2f),
    onSecondaryContainer = SecondaryTealDark,
    tertiary = AlertOrange,
    onTertiary = Color.White,
    tertiaryContainer = AlertRed.copy(alpha = 0.1f),
    onTertiaryContainer = AlertRedDark,
    error = AlertRed,
    onError = Color.White,
    errorContainer = AlertRed.copy(alpha = 0.1f),
    onErrorContainer = AlertRedDark,
    background = BackgroundLight,
    onBackground = Gray900,
    surface = SurfaceLight,
    onSurface = Gray900,
    surfaceVariant = Gray100,
    onSurfaceVariant = Gray600,
    outline = Gray300
)

@Composable
fun SafeRouteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}