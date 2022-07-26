package com.example.madplayground.app.modules

import com.example.madplayground.ui.logs.source.AndroidLogsController
import com.example.madplayground.common.logs.models.Logs
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