package com.example.auth0.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.example.auth0.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authenticationService: AuthenticationService): ViewModel() {
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val isLoginLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun login(context: Context, connection: String) {
        viewModelScope.launch {
            try {
                val credentials = authenticationService.auth(context, connection)
                isLoginLiveData.postValue(true)
            } catch (error: CredentialsManagerException) {
                isLoginLiveData.postValue(false)
                errorLiveData.postValue(error)
            }
        }
    }
}