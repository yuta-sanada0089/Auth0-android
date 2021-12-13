package com.example.auth0.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.example.auth0.service.AuthenticationService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AuthenticationService(this)
    }

    private suspend fun checkCredencials() {
        try {
            val credencials = AuthenticationService(this).credentioals()
        } catch (error: CredentialsManagerException) {

        }
    }
}