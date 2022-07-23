package com.example.madplayground.domain.settings.source

import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.CycleNavigationLabelVisibilityUseCase

class CycleNavigationLabelVisibilityUseCaseImpl(
    private val settings: Settings
) : CycleNavigationLabelVisibilityUseCase {

    override suspend fun invoke() {

        val newVisibility = when (
            settings.navigationLabelVisibility.value
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

        return settings.setNavigationLabelVisibility(newVisibility)
    }

}