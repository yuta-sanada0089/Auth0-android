package com.example.auth0

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.auth0.screens.LoginScreen
import com.example.auth0.screens.SplashScreen
import com.example.auth0.screens.TopScreen

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("top") {
            TopScreen(navController = navController)
        }
    }
}
