package com.example.madplayground.app.modules

import com.example.madplayground.app.models.App
import com.example.madplayground.app.source.ApplicationController
import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.moments.models.Moments
import com.example.madplayground.domain.settings.models.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApp(
        logs: Logs,
        settings: Settings,
        moments: Moments,
    ): App {
        return ApplicationController(
            logs = logs,
            settings = settings,
            moments = moments,
        )
    }

}
