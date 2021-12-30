package com.example.auth0.di

import android.content.Context
import com.example.auth0.service.AuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesAuthenticationService(@ApplicationContext context: Context): AuthenticationService {
        return AuthenticationService(context)
    }
}
