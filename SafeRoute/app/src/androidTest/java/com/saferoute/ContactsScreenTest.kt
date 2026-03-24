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
class ContactsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    @DisplayName("Contacts screen displays correctly")
    fun contactsScreen_displaysCorrectly() {
        // Login and navigate to contacts
        composeTestRule.onNodeWithText("Code PIN").performTextInput("1234")
        composeTestRule.onNodeWithText("Se connecter").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Contacts").performClick()
        composeTestRule.waitForIdle()

        // Check for title
        composeTestRule.onNodeWithText("Contacts d'urgence").assertIsDisplayed()
    }

    @Test
    @DisplayName("Add contact button is displayed")
    fun contactsScreen_addButtonDisplayed() {
        // Login and navigate to contacts
        composeTestRule.onNodeWithText("Code PIN").performTextInput("1234")
        composeTestRule.onNodeWithText("Se connecter").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Contacts").performClick()
        composeTestRule.waitForIdle()

        // Check for FAB
        composeTestRule.onNodeWithContentDescription("Ajouter un contact").assertIsDisplayed()
    }
}