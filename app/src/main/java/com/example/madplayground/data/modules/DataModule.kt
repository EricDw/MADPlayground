package com.example.madplayground.data.modules

import com.example.madplayground.data.repositories.SettingsRepository
import com.example.madplayground.data.sources.SettingsDataSource
import com.example.madplayground.models.apis.Settings
import com.example.madplayground.models.apis.Settings.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideSettingsDataSource(): DataSource {
        return SettingsDataSource()
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(
        settingsDataSource: DataSource,
    ): Settings.Repository {
        return SettingsRepository(
            settingsDataSource = settingsDataSource
        )
    }

}