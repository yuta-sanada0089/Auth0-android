package com.example.auth0.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.result.Credentials
import com.example.auth0.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authenticationService: AuthenticationService): ViewModel() {
    val isLoginLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val credentialsLivedata: MutableLiveData<Credentials> = MutableLiveData()

    fun fetchCredential() {
        viewModelScope.launch {
            try {
                val credentials = authenticationService.credentials()
                credentialsLivedata.postValue(credentials)
                isLoginLiveData.postValue(true)
            } catch (error: CredentialsManagerException) {
                isLoginLiveData.postValue(false)
            }
        }
    }
}
