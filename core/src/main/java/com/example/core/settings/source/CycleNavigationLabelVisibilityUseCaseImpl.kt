package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsRepository
import com.example.core.settings.usecases.CycleNavigationLabelVisibilityUseCase

class CycleNavigationLabelVisibilityUseCaseImpl(
    private val repository: SettingsRepository,
) : CycleNavigationLabelVisibilityUseCase {

    override suspend fun invoke() {

        val settings = repository.retrieveSettings()

        settings.cycleNavigationLabelVisibility()

        return repository.updateSettings(settings)
    }

}