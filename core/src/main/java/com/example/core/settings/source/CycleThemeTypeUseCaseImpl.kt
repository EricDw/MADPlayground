package com.example.core.settings.source

import com.example.core.settings.repository.SettingsRepository
import com.example.core.settings.usecases.CycleThemeTypeUseCase

class CycleThemeTypeUseCaseImpl(
    private val repository: SettingsRepository,
) : CycleThemeTypeUseCase {

    override suspend fun invoke() {

        val settings = repository.retrieveSettings()

        settings.cycleThemeType()

        repository.updateSettings(settings)

    }

}