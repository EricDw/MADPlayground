package com.example.madplayground.domain.settings.source

import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.CycleIconographyTypeUseCase
import com.example.madplayground.domain.settings.usecases.CycleThemeTypeUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveThemeTypeUseCase

class CycleIconographyTypeUseCaseImpl(
    private val settings: Settings,
) : CycleIconographyTypeUseCase {

    override suspend fun invoke() {

        val newIconographyType = when (
            settings.iconographyType.value
        ) {

            Settings.IconographyType.DEFAULT  -> {
                Settings.IconographyType.SHARP
            }

            Settings.IconographyType.SHARP    -> {
                Settings.IconographyType.OUTLINED
            }

            Settings.IconographyType.OUTLINED -> {
                Settings.IconographyType.ROUNDED
            }

            Settings.IconographyType.ROUNDED  -> {
                Settings.IconographyType.TWO_TONE
            }

            Settings.IconographyType.TWO_TONE -> {
                Settings.IconographyType.DEFAULT
            }
        }

        return settings.setIconographyType(newIconographyType)
    }

}