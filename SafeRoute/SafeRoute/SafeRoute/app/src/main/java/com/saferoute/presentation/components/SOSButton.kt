package com.saferoute.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.saferoute.presentation.theme.AlertRed
import com.saferoute.presentation.theme.AlertRedDark
import com.saferoute.presentation.theme.SOSGradientEnd
import com.saferoute.presentation.theme.SOSGradientStart
import kotlinx.coroutines.delay

@Composable
fun SOSButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    countdownSeconds: Int = 5
) {
    var isPressed by remember { mutableStateOf(false) }
    var countdown by remember { mutableStateOf(countdownSeconds) }
    var isCountingDown by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressedState by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressedState) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scale"
    )

    val pulseAnimation by rememberInfiniteTransition(label = "pulse").animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    LaunchedEffect(isPressedState) {
        if (isPressedState && !isCountingDown) {
            isPressed = true
            isCountingDown = true
            countdown = countdownSeconds

            while (countdown > 0) {
                delay(1000)
                countdown--
            }

            if (isPressed) {
                onClick()
            }

            isCountingDown = false
            isPressed = false
        } else if (!isPressedState) {
            isPressed = false
            isCountingDown = false
            countdown = countdownSeconds
        }
    }

    Box(
        modifier = modifier
            .scale(scale)
            .shadow(
                elevation = if (isPressedState) 8.dp else 20.dp,
                shape = CircleShape,
                spotColor = AlertRed.copy(alpha = 0.5f)
            )
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(SOSGradientStart, SOSGradientEnd)
                ),
                shape = CircleShape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { }
            ),
        contentAlignment = Alignment.Center
    ) {
        // Outer pulse ring
        if (!isPressed) {
            Box(
                modifier = Modifier
                    .fillMaxSize(pulseAnimation)
                    .background(
                        color = AlertRed.copy(alpha = 0.2f),
                        shape = CircleShape
                    )
            )
        }

        // Inner content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isCountingDown) {
                Text(
                    text = countdown.toString(),
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Relâchez pour annuler",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Sos,
                    contentDescription = "SOS",
                    modifier = Modifier.size(64.dp),
                    tint = Color.White
                )

                Text(
                    text = "SOS",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Maintenez pour alerter",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}