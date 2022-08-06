package com.example.core.settings.source

import com.example.core.settings.repository.SettingsRepository
import com.example.core.settings.usecases.CycleIconographyTypeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CycleIconographyTypeUseCaseImpl(
    private val repository: SettingsRepository,
) : CycleIconographyTypeUseCase {

    override suspend fun invoke() {

        withContext(Dispatchers.IO) {

            val settings = repository.retrieveSettings()

            settings.cycleIconographyType()

            repository.updateSettings(settings)

        }
    }

}