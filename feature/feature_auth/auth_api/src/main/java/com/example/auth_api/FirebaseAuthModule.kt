package com.example.auth_api

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FirebaseAuthModule {
    @Provides
    @Singleton
    fun provideFireAuthClient(): FirebaseAuth = FirebaseAuth.getInstance()
}
