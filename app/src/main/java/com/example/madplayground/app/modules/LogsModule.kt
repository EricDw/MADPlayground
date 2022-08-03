package com.example.madplayground.app.modules

import com.example.common.logs.models.Logs
import com.example.madplayground.ui.logs.source.AndroidLogsController
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
    fun provideLogs(): com.example.common.logs.models.Logs {
        return AndroidLogsController()
    }

}