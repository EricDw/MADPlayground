package com.example.core.settings.repository

import com.example.core.settings.models.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {

    suspend fun retrieveSettings(): Settings

    suspend fun updateSettings(
        settings: Settings
    )

    fun retrieveSettingsFlow() : Flow<Settings>

}