package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsCache
import com.example.core.settings.usecases.RetrieveThemeTypeUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveThemeTypeUseCaseImpl(
    private val cache: SettingsCache,
) : RetrieveThemeTypeUseCase {

    override fun invoke(): Flow<Settings.ThemeType> {
        return cache.themeType
    }

}