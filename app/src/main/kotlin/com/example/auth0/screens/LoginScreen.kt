package com.example.auth0.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.example.auth0.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = hiltViewModel()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context: Context = LocalContext.current

    val lifecycleObserver = remember {
        LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                viewModel.isLoginLiveData.observe(lifecycleOwner) { isLogin ->
                    if (isLogin) {
                        navController.navigate("top")
                    }
                }
                viewModel.errorLiveData.observe(lifecycleOwner) {
                    it.message?.let { message -> showToastMessage(context, message) }
                }
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose { lifecycleOwner.lifecycle.removeObserver(lifecycleObserver) }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "LoginScreen")
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp))
        Button(onClick = { viewModel.login(context, "Username-Password-Authentication") }) {
            Text(text = "メールアドレスでログイン")
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp))
        Button(onClick = { viewModel.login(context, "google-oauth2") }) {
            Text(text = "Googleでログイン")
        }
    }
}

private fun showToastMessage(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}