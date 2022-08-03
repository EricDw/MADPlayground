package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsCache
import com.example.core.settings.usecases.CycleIconographyTypeUseCase

class CycleIconographyTypeUseCaseImpl(
    private val cache: SettingsCache,
) : CycleIconographyTypeUseCase {

    override suspend fun invoke() {

        val newIconographyType = when (
            cache.iconographyType.value
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

        return cache.setIconographyType(newIconographyType)
    }

}