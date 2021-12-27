package com.example.auth0.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.Observer
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.auth0.screens.LoginScreen
import com.example.auth0.screens.SplashScreen
import com.example.auth0.screens.TopScreen
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
            if (it.idToken != null) {

            }
        })
    }
}

@Composable
fun MainScreen() {
    val viewModel = hiltNavGraphViewModel<MainViewModel>()

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen()
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("top") {
            TopScreen(navController)
        }
    }
}
