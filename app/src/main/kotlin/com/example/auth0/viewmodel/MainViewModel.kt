package com.example.auth0.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.result.Credentials
import com.example.auth0.service.AuthenticationService
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val credentialsLivedata: MutableLiveData<Credentials> = MutableLiveData()
    private val authenticationService = AuthenticationService(getApplication<Application>().applicationContext)

    fun fetchCredential() {
        viewModelScope.launch {
            try {
                val credentials = authenticationService.credentioals()
                credentialsLivedata.postValue(credentials)
            } catch (error: CredentialsManagerException) {
                errorLiveData.postValue(error)
            }
        }
    }
}
