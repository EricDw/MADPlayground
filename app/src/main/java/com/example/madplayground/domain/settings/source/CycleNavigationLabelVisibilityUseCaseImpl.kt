package com.example.madplayground.domain.settings.source

import com.example.madplayground.cache.settings.models.SettingsCache
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.CycleNavigationLabelVisibilityUseCase

class CycleNavigationLabelVisibilityUseCaseImpl(
    private val cache: SettingsCache,
) : CycleNavigationLabelVisibilityUseCase {

    override suspend fun invoke() {

        val newVisibility = when (
            cache.navigationLabelVisibility.value
        ) {

            Settings.NavigationLabelVisibility.NEVER         -> {
                Settings.NavigationLabelVisibility.ALWAYS
            }

            Settings.NavigationLabelVisibility.ALWAYS        -> {
                Settings.NavigationLabelVisibility.WHEN_SELECTED
            }

            Settings.NavigationLabelVisibility.WHEN_SELECTED -> {
                Settings.NavigationLabelVisibility.NEVER
            }

        }

        return cache.setNavigationLabelVisibility(newVisibility)
    }

}