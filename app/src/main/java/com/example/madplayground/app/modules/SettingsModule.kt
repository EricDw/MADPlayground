package com.example.madplayground.app.modules

import com.example.core.settings.repository.SettingsCache
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
        cache: SettingsCache,
    ): RetrieveThemeTypeUseCase {

        return RetrieveThemeTypeUseCaseImpl(
            cache = cache
        )

    }

    @Provides
    fun provideRetrieveIconographyTypeUseCase(
        cache: SettingsCache,
    ): RetrieveIconographyTypeUseCase {

        return RetrieveIconographyTypeUseCaseImpl(
            cache = cache
        )

    }

    @Provides
    fun provideRetrieveShapeTypeUseCase(
        cache: SettingsCache,
    ): RetrieveShapeTypeUseCase {

        return RetrieveShapeTypeUseCaseImpl(
            cache = cache
        )

    }

    @Provides
    fun provideRetrieveNavTypeUseCase(
        cache: SettingsCache,
    ): RetrieveNavigationLabelVisibilityUseCase {

        return RetrieveNavigationLabelVisibilityUseCaseImpl(
            cache = cache
        )

    }

    @Provides
    fun provideCycleThemeTypeUseCase(
        cache: SettingsCache,
    ): CycleThemeTypeUseCase {

        return CycleThemeTypeUseCaseImpl(
            cache = cache
        )

    }

    @Provides
    fun provideCycleIconographyTypeUseCase(
        cache: SettingsCache,
    ): CycleIconographyTypeUseCase {

        return CycleIconographyTypeUseCaseImpl(
            cache = cache
        )

    }

    @Provides
    fun provideCycleShapeTypeUseCase(
        cache: SettingsCache,
    ): CycleShapeTypeUseCase {

        return CycleShapeTypeUseCaseImpl(
            cache = cache
        )

    }

    @Provides
    fun provideCycleNavigationLabelVisibilityUseCase(
        cache: SettingsCache,
    ): CycleNavigationLabelVisibilityUseCase {

        return CycleNavigationLabelVisibilityUseCaseImpl(
            cache = cache
        )

    }


}