package com.example.auth0.service

import android.app.Activity
import android.content.Context
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.CredentialsManager
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.authentication.storage.SharedPreferencesStorage
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.example.auth0.R
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthenticationService @Inject constructor(private val context: Context) {
    private val client = Auth0(
        context.getString(R.string.com_auth0_client_id),
        context.getString(R.string.com_auth0_domain)
    )

    private val apiClient = AuthenticationAPIClient(client)
    private val credentialManager = CredentialsManager(apiClient, SharedPreferencesStorage(context))

    suspend fun credentials(): Credentials =
        suspendCancellableCoroutine { continuetion ->
            credentialManager.getCredentials(object : Callback<Credentials, CredentialsManagerException> {
                override fun onFailure(error: CredentialsManagerException) {
                    continuetion.resumeWithException(error)
                }
                override fun onSuccess(result: Credentials) {
                    continuetion.resume(result)
                }
            })
        }

    suspend fun auth(connection: String): Credentials =
        suspendCancellableCoroutine { continuetion ->
            WebAuthProvider.login(client)
                .withAudience("https://${R.string.com_auth0_domain}/userinfo")
                .withScheme(context.packageName)
                .withScope("openid email offline_access")
                .withConnection(connection)
                .withParameters(mapOf("max_age" to "30"))
                .start(context, object : Callback<Credentials, AuthenticationException> {
                    override fun onFailure(error: AuthenticationException) {
                        RuntimeException("Failed to auth")
                        continuetion.resumeWithException(error)
                    }

                    override fun onSuccess(result: Credentials) {
                        credentialManager.saveCredentials(result)
                        continuetion.resume(result)
                    }
                })
        }

    fun logout() {
        credentialManager.clearCredentials()
    }
}
