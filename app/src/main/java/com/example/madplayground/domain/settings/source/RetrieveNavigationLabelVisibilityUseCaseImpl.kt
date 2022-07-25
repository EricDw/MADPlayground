package com.example.madplayground.domain.settings.source

import com.example.madplayground.cache.settings.models.SettingsCache
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.RetrieveNavigationLabelVisibilityUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveNavigationLabelVisibilityUseCaseImpl(
    private val cache: SettingsCache
) : RetrieveNavigationLabelVisibilityUseCase {

    override fun invoke(): Flow<Settings.NavigationLabelVisibility> {
        return cache.navigationLabelVisibility
    }

}