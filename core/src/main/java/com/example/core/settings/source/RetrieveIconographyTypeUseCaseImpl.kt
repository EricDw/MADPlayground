package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsCache
import com.example.core.settings.usecases.RetrieveIconographyTypeUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveIconographyTypeUseCaseImpl(
    private val cache: SettingsCache
) : RetrieveIconographyTypeUseCase {

    override fun invoke(): Flow<Settings.IconographyType> {
        return cache.iconographyType
    }

}