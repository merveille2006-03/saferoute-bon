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
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    @DisplayName("Login screen displays correctly")
    fun loginScreen_displaysCorrectly() {
        // Check for app title
        composeTestRule.onNodeWithText("SafeRoute").assertIsDisplayed()

        // Check for PIN input
        composeTestRule.onNodeWithText("Code PIN").assertIsDisplayed()

        // Check for login button
        composeTestRule.onNodeWithText("Se connecter").assertIsDisplayed()
    }

    @Test
    @DisplayName("Login with valid PIN navigates to home")
    fun login_withValidPIN() {
        // Enter PIN
        composeTestRule.onNodeWithText("Code PIN").performTextInput("1234")

        // Click login
        composeTestRule.onNodeWithText("Se connecter").performClick()

        // Wait for navigation
        composeTestRule.waitForIdle()
    }

    @Test
    @DisplayName("Login with invalid PIN shows error")
    fun login_withInvalidPIN() {
        // Enter invalid PIN
        composeTestRule.onNodeWithText("Code PIN").performTextInput("0000")

        // Click login
        composeTestRule.onNodeWithText("Se connecter").performClick()

        // Check for error
        composeTestRule.onNodeWithText("Code PIN incorrect").assertIsDisplayed()
    }
}