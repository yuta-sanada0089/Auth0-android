package com.example.auth0.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.result.Credentials
import com.example.auth0.service.AuthenticationService
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val credentialsLivedata: MutableLiveData<Credentials> = MutableLiveData()
    private val authenticationService = AuthenticationService(getApplication<Application>().applicationContext)

    fun login(activity: Activity, connection: String) {
        viewModelScope.launch {
            try {
                val credentials = authenticationService.auth(activity, connection)
                credentialsLivedata.postValue(credentials)
            } catch (error: CredentialsManagerException) {
                errorLiveData.postValue(error)
            }
        }
    }
}