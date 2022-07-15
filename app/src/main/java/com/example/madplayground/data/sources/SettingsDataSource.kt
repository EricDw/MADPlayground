package com.example.madplayground.data.sources

import com.example.madplayground.models.SettingsController
import com.example.madplayground.models.apis.Settings
import javax.inject.Inject

class SettingsDataSource @Inject constructor() : Settings.DataSource {

    private var currentSettings = SettingsController()

    override fun getDefaultSettings(): Settings {
        return SettingsController()
    }

    override suspend fun insertSettings(
        newSettings: Settings,
    ) {

    }

    override suspend fun getSettings(): Settings {
        return currentSettings
    }

}