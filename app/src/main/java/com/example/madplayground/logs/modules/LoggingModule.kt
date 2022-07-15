package com.example.madplayground.logs.modules

import com.example.madplayground.logs.AndroidLogger
import com.example.madplayground.logs.api.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggingModule {

    @Provides
    @Singleton
    fun provideLogger(): Logger {
        return AndroidLogger()
    }

}