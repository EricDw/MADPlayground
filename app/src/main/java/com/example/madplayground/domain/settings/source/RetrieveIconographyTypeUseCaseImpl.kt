package com.example.madplayground.domain.settings.source

import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.RetrieveIconographyTypeUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveThemeTypeUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveIconographyTypeUseCaseImpl(
    private val settings: Settings
) : RetrieveIconographyTypeUseCase {

    override fun invoke(): Flow<Settings.IconographyType> {
        return settings.iconographyType
    }

}