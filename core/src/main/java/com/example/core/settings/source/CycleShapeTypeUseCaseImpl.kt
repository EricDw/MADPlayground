package com.example.core.settings.source

import com.example.core.settings.repository.SettingsRepository
import com.example.core.settings.usecases.CycleShapeTypeUseCase

class CycleShapeTypeUseCaseImpl(
    private val repository: SettingsRepository,
) : CycleShapeTypeUseCase {

    override suspend fun invoke() {

        val settings = repository.retrieveSettings()

        settings.cycleShapeType()

        repository.updateSettings(settings)

    }

}