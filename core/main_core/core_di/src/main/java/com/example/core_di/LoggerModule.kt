package com.example.core_di

import com.example.core_api.LoggerApp
import com.example.core_inner.logger.LoggerAppImpl
import dagger.Binds
import dagger.Module

@Module
interface LoggerModule {
    @Binds
    fun bindLogger(loggerAppImpl: LoggerAppImpl): LoggerApp
}