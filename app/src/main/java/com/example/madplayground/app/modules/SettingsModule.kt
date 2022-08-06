package com.example.madplayground.app.modules

import com.example.core.settings.repository.SettingsRepository
import com.example.core.settings.source.*
import com.example.core.settings.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    fun provideRetrieveThemeTypeUseCase(
        repository: SettingsRepository,
    ): RetrieveThemeTypeUseCase {

        return RetrieveThemeTypeUseCaseImpl(
            repository = repository
        )

    }

    @Provides
    fun provideRetrieveIconographyTypeUseCase(
        repository: SettingsRepository,
    ): RetrieveIconographyTypeUseCase {

        return RetrieveIconographyTypeUseCaseImpl(
            repository = repository
        )

    }

    @Provides
    fun provideRetrieveShapeTypeUseCase(
        repository: SettingsRepository,
    ): RetrieveShapeTypeUseCase {

        return RetrieveShapeTypeUseCaseImpl(
            repository = repository
        )

    }

    @Provides
    fun provideRetrieveNavTypeUseCase(
        repository: SettingsRepository,
    ): RetrieveNavigationLabelVisibilityUseCase {

        return RetrieveNavigationLabelVisibilityUseCaseImpl(
            repository = repository
        )

    }

    @Provides
    fun provideCycleThemeTypeUseCase(
        repository: SettingsRepository,
    ): CycleThemeTypeUseCase {

        return CycleThemeTypeUseCaseImpl(
            repository = repository
        )

    }

    @Provides
    fun provideCycleIconographyTypeUseCase(
        repository: SettingsRepository,
    ): CycleIconographyTypeUseCase {

        return CycleIconographyTypeUseCaseImpl(
            repository = repository
        )

    }

    @Provides
    fun provideCycleShapeTypeUseCase(
        repository: SettingsRepository,
    ): CycleShapeTypeUseCase {

        return CycleShapeTypeUseCaseImpl(
            repository = repository
        )

    }

    @Provides
    fun provideCycleNavigationLabelVisibilityUseCase(
        repository: SettingsRepository,
    ): CycleNavigationLabelVisibilityUseCase {

        return CycleNavigationLabelVisibilityUseCaseImpl(
            repository = repository
        )

    }


}