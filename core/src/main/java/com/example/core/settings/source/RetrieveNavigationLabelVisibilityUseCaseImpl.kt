package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsRepository
import com.example.core.settings.usecases.RetrieveNavigationLabelVisibilityUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RetrieveNavigationLabelVisibilityUseCaseImpl(
    private val repository: SettingsRepository
) : RetrieveNavigationLabelVisibilityUseCase {

    override fun invoke(): Flow<Settings.NavigationLabelVisibility> {
        return repository.retrieveSettingsFlow().map { it.navigationLabelVisibility }
    }

}