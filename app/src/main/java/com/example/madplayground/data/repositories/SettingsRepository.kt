package com.example.madplayground.data.repositories

import com.example.madplayground.models.apis.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val settingsDataSource: Settings.DataSource,
) : Settings.Repository {

    private val currentSettings = MutableStateFlow(
        settingsDataSource.getDefaultSettings()
    )

    private val settingsFlow = currentSettings.asStateFlow()

    override suspend fun getSettings(): Settings {
        return settingsDataSource.getSettings()
    }

    override suspend fun insertSettings(
        newSettings: Settings,
    ) {
        settingsDataSource.insertSettings(newSettings)
        currentSettings.update {
            settingsDataSource.getSettings()
        }
    }

    override fun getSettingsFlow(): Flow<Settings> {
        return settingsFlow
    }

}