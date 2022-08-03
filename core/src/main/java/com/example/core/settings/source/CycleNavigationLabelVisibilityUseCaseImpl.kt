package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsCache
import com.example.core.settings.usecases.CycleNavigationLabelVisibilityUseCase

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