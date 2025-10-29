import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat

fun showBiometricPrompt(onSuccess: () -> Unit, onFailure: () -> Unit) {
    val executor = ContextCompat.getMainExecutor(context)
    val biometricPrompt = BiometricPrompt(this, executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onSuccess()
            }
            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                onFailure()
            }
        })

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Biometric login")
        .setSubtitle("Login using your fingerprint or face")
        .setNegativeButtonText("Cancel")
        .build()

    biometricPrompt.authenticate(promptInfo)
}

val biometricManager = BiometricManager.from(context)
if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
    showBiometricPrompt(
        onSuccess = { /* proceed to app/dashboard */ },
        onFailure = { /* fallback to password login */ }
    )
}
