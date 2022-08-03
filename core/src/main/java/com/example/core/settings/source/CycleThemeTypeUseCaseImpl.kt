package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsCache
import com.example.core.settings.usecases.CycleThemeTypeUseCase

class CycleThemeTypeUseCaseImpl(
    private val cache: SettingsCache,
) : CycleThemeTypeUseCase {

    override suspend fun invoke() {

        val newThemeType = when (
            cache.themeType.value
        ) {

            Settings.ThemeType.LIGHT  -> {
                Settings.ThemeType.DARK
            }

            Settings.ThemeType.DARK   -> {
                Settings.ThemeType.SYSTEM
            }

            Settings.ThemeType.SYSTEM -> {
                Settings.ThemeType.LIGHT
            }

        }

        return cache.setThemeType(newThemeType)
    }

}