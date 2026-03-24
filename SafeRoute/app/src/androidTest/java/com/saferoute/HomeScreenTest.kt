package com.saferoute

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.saferoute.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.jupiter.api.DisplayName

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    @DisplayName("Home screen displays status cards")
    fun homeScreen_displaysStatusCards() {
        // Login first
        composeTestRule.onNodeWithText("Code PIN").performTextInput("1234")
        composeTestRule.onNodeWithText("Se connecter").performClick()
        composeTestRule.waitForIdle()

        // Check for status cards
        composeTestRule.onNodeWithText("Protection").assertIsDisplayed()
        composeTestRule.onNodeWithText("GPS").assertIsDisplayed()
        composeTestRule.onNodeWithText("Zones").assertIsDisplayed()
    }

    @Test
    @DisplayName("SOS button is displayed")
    fun homeScreen_sosButtonDisplayed() {
        // Login first
        composeTestRule.onNodeWithText("Code PIN").performTextInput("1234")
        composeTestRule.onNodeWithText("Se connecter").performClick()
        composeTestRule.waitForIdle()

        // Check for SOS button
        composeTestRule.onNodeWithText("SOS").assertIsDisplayed()
    }

    @Test
    @DisplayName("Navigation buttons are displayed")
    fun homeScreen_navigationButtonsDisplayed() {
        // Login first
        composeTestRule.onNodeWithText("Code PIN").performTextInput("1234")
        composeTestRule.onNodeWithText("Se connecter").performClick()
        composeTestRule.waitForIdle()

        // Check for navigation buttons
        composeTestRule.onNodeWithText("Contacts").assertIsDisplayed()
        composeTestRule.onNodeWithText("Zones").assertIsDisplayed()
        composeTestRule.onNodeWithText("Historique").assertIsDisplayed()
    }
}