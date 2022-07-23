package com.example.madplayground.app.modules

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.madplayground.data.settings.source.SettingsController
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.source.*
import com.example.madplayground.domain.settings.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettings(
        @ApplicationContext context: Context,
    ): Settings {

        val ioScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

        val dataStore = PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("settings")
        }

        return SettingsController(
            dataStore = dataStore,
            scope = ioScope
        )

    }

    @Provides
    fun provideRetrieveThemeTypeUseCase(
        settings: Settings
    ): RetrieveThemeTypeUseCase {

        return RetrieveThemeTypeUseCaseImpl(
            settings = settings
        )

    }

    @Provides
    fun provideRetrieveIconographyTypeUseCase(
        settings: Settings
    ): RetrieveIconographyTypeUseCase {

        return RetrieveIconographyTypeUseCaseImpl(
            settings = settings
        )

    }

    @Provides
    fun provideRetrieveShapeTypeUseCase(
        settings: Settings
    ): RetrieveShapeTypeUseCase {

        return RetrieveShapeTypeUseCaseImpl(
            settings = settings
        )

    }

    @Provides
    fun provideRetrieveNavTypeUseCase(
        settings: Settings
    ): RetrieveNavigationLabelVisibilityUseCase {

        return RetrieveNavigationLabelVisibilityUseCaseImpl(
            settings = settings
        )

    }

    @Provides
    fun provideCycleThemeTypeUseCase(
        settings: Settings
    ): CycleThemeTypeUseCase {

        return CycleThemeTypeUseCaseImpl(
            settings = settings
        )

    }

    @Provides
    fun provideCycleIconographyTypeUseCase(
        settings: Settings
    ): CycleIconographyTypeUseCase {

        return CycleIconographyTypeUseCaseImpl(
            settings = settings
        )

    }

    @Provides
    fun provideCycleShapeTypeUseCase(
        settings: Settings
    ): CycleShapeTypeUseCase {

        return CycleShapeTypeUseCaseImpl(
            settings = settings
        )

    }

    @Provides
    fun provideCycleNavigationLabelVisibilityUseCase(
        settings: Settings
    ): CycleNavigationLabelVisibilityUseCase {

        return CycleNavigationLabelVisibilityUseCaseImpl(
            settings = settings
        )

    }


}