package com.example.madplayground.domain.settings.source

import com.example.madplayground.cache.settings.models.SettingsCache
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.RetrieveIconographyTypeUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveIconographyTypeUseCaseImpl(
    private val cache: SettingsCache
) : RetrieveIconographyTypeUseCase {

    override fun invoke(): Flow<Settings.IconographyType> {
        return cache.iconographyType
    }

}