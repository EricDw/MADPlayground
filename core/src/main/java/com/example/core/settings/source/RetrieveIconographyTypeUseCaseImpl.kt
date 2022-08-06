package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsRepository
import com.example.core.settings.usecases.RetrieveIconographyTypeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RetrieveIconographyTypeUseCaseImpl(
    private val repository: SettingsRepository
) : RetrieveIconographyTypeUseCase {

    override fun invoke(): Flow<Settings.IconographyType> {
        return repository.retrieveSettingsFlow().map { it.iconographyType }
    }

}