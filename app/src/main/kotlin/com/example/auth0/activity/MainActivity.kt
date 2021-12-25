package com.example.auth0.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.Observer
import androidx.navigation.compose.rememberNavController
import com.example.auth0.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchCredential()

        viewModel.credentialsLivedata.observe(this, Observer {
            val intent = Intent(application, TopActivity::class.java)
            startActivity(intent)
        })

        viewModel.errorLiveData.observe(this, Observer {
            val intent = Intent(application, LoginActivity::class.java)
            startActivity(intent)
        })
    }
}

@Composable
fun MainAppEntry() {
    val navController = rememberNavController()
}
