package com.example.madplayground.features.app.modules

import com.example.madplayground.features.app.apis.App
import com.example.madplayground.features.app.controllers.ApplicationController
import com.example.madplayground.features.logs.api.Logs
import com.example.madplayground.features.quotes.apis.Quotes
import com.example.madplayground.features.settings.apis.Settings
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
        quotes: Quotes,
    ): App {
        return ApplicationController(
            logs = logs,
            settings = settings,
            quotes = quotes,
        )
    }

}
