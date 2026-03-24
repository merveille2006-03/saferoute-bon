package com.saferoute.presentation.viewmodels

import com.saferoute.domain.model.AuthResult
import android.app.Application
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    private val _biometricState = MutableStateFlow<BiometricState>(BiometricState.NotAvailable)
    val biometricState: StateFlow<BiometricState> = _biometricState

    private val biometricManager = BiometricManager.from(application)

    init {
        checkBiometricAvailability()
    }

    private fun checkBiometricAvailability() {
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                _biometricState.value = BiometricState.Available
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                _biometricState.value = BiometricState.NotAvailable
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                _biometricState.value = BiometricState.NotAvailable
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                _biometricState.value = BiometricState.NotEnrolled
            }
            else -> {
                _biometricState.value = BiometricState.NotAvailable
            }
        }
    }

    fun authenticateWithBiometric(activity: FragmentActivity) {
        val executor = ContextCompat.getMainExecutor(getApplication())

        val biometricPrompt = BiometricPrompt(activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    _isAuthenticated.value = true
                    Timber.d("Biometric authentication succeeded")
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    _isAuthenticated.value = false
                    Timber.d("Biometric authentication failed")
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    _isAuthenticated.value = false
                    Timber.e("Biometric authentication error: $errString")
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Authentification requise")
            .setSubtitle("Utilisez votre empreinte digitale pour accéder à SafeRoute")
            .setNegativeButtonText("Annuler")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    fun loginWithPin(pin: String): Boolean {
        // Simple PIN validation - in production, use secure storage
        return if (pin == "1234") {
            _isAuthenticated.value = true
            true
        } else {
            false
        }
    }

    fun logout() {
        _isAuthenticated.value = false
    }

    sealed class BiometricState {
        object Available : BiometricState()
        object NotAvailable : BiometricState()
        object NotEnrolled : BiometricState()
    }
}