package com.example.madplayground.features.logs.modules

import com.example.madplayground.features.logs.controllers.AndroidLogsController
import com.example.madplayground.features.logs.api.Logs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LogsModule {

    @Provides
    @Singleton
    fun provideLogs(): Logs {
        return AndroidLogsController()
    }

}