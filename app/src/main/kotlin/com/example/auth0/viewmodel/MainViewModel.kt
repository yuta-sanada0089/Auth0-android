package com.example.auth0.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.result.Credentials
import com.example.auth0.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authenticationService: AuthenticationService): ViewModel() {
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val credentialsLivedata: MutableLiveData<Credentials> = MutableLiveData()

    fun fetchCredential() {
        viewModelScope.launch {
            try {
                val credentials = authenticationService.credentials()
                credentialsLivedata.postValue(credentials)
            } catch (error: CredentialsManagerException) {
                errorLiveData.postValue(error)
            }
        }
    }
}
