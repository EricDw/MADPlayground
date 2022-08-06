package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsRepository
import com.example.core.settings.usecases.RetrieveThemeTypeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RetrieveThemeTypeUseCaseImpl(
    private val repository: SettingsRepository,
) : RetrieveThemeTypeUseCase {

    override fun invoke(): Flow<Settings.ThemeType> {
        return repository.retrieveSettingsFlow().map { it.themeType }
    }

}