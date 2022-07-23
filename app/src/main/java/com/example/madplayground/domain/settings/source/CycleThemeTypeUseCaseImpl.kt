package com.example.madplayground.domain.settings.source

import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.CycleThemeTypeUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveThemeTypeUseCase

class CycleThemeTypeUseCaseImpl(
    private val settings: Settings,
) : CycleThemeTypeUseCase {

    override suspend fun invoke() {

        val newThemeType = when (
            settings.themeType.value
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

        return settings.setThemeType(newThemeType)
    }

}