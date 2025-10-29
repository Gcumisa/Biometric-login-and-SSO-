package com.example.snakeice

class MainActivity {
    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken(getString(R.string.server_client_id)) // your OAuth client ID
        .build()

    private val googleSignInClient = GoogleSignIn.getClient(this, gso)

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken
                // Send ID token to your backend for validation & registration/login
            } catch (e: ApiException) {
                Log.e(TAG, "Google sign in failed: ${e.statusCode}")
            }
        }
    }

}