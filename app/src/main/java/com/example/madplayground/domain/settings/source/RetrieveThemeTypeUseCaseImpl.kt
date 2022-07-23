package com.example.madplayground.domain.settings.source

import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.RetrieveThemeTypeUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveThemeTypeUseCaseImpl(
    private val settings: Settings
) : RetrieveThemeTypeUseCase {

    override fun invoke(): Flow<Settings.ThemeType> {
        return settings.themeType
    }

}