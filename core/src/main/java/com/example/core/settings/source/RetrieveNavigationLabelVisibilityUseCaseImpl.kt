package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsCache
import com.example.core.settings.usecases.RetrieveNavigationLabelVisibilityUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveNavigationLabelVisibilityUseCaseImpl(
    private val cache: SettingsCache
) : RetrieveNavigationLabelVisibilityUseCase {

    override fun invoke(): Flow<Settings.NavigationLabelVisibility> {
        return cache.navigationLabelVisibility
    }

}