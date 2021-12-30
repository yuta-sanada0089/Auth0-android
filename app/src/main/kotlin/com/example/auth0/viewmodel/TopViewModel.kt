package com.example.auth0.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth0.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(private val authenticationService: AuthenticationService): ViewModel() {

    fun logout() {
        viewModelScope.launch {
            authenticationService.logout()
        }
    }

}