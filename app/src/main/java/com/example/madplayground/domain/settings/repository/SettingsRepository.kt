package com.example.madplayground.domain.settings.repository

import com.example.madplayground.domain.settings.models.Settings

interface SettingsRepository {
    
    suspend fun setSettings(
        settings: Settings
    )

    suspend fun getSettings(): Settings?
    
}