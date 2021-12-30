package com.example.auth0.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.auth0.viewmodel.TopViewModel

@Composable
fun TopScreen(navController: NavController) {
    val viewModel: TopViewModel = hiltViewModel()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "topScreen")
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp))
        Button(onClick = {
            viewModel.logout()
            navController.popBackStack()
        }) {
            Text(text = "ログアウト")
        }
    }
}
