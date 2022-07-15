package com.example.madplayground.app.modules

import com.example.madplayground.app.ApplicationController
import com.example.madplayground.models.apis.App
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
        application: ApplicationController,
    ): App {
        return application
    }

}