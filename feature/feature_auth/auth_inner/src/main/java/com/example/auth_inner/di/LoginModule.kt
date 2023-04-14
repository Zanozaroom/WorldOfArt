package com.example.auth_inner.di

import dagger.Module

@Module(includes = [LoginVMFactory::class, NavigatorAuthModule::class])
class LoginModule

