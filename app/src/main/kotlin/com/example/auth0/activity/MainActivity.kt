package com.example.auth0.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.auth0.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModel(application)
        viewModel.fetchCredential()

        viewModel.credentialsLivedata.observe(this, Observer {
            val intent = Intent(application, TopActivity::class.java)
            startActivity(intent)
        })

        viewModel.errorLiveData.observe(this, Observer {
            val intent = Intent(application, LoginActivity::class.java)
            startActivity(intent)
        })
    }
}
