package com.example.madplayground.domain.settings.source

import com.example.madplayground.cache.settings.models.SettingsCache
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.CycleThemeTypeUseCase

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