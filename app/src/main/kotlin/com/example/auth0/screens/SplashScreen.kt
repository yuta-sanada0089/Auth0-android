package com.example.auth0.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.example.auth0.viewmodel.SplashViewModel

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {
    viewModel.fetchCredential()

    val lifecycleOwner = LocalLifecycleOwner.current
    viewModel.isLoginLiveData.observe(lifecycleOwner, Observer {
        when(it) {
            true -> navController.navigate("top")
            false -> navController.navigate("login")
        }
    })
}
