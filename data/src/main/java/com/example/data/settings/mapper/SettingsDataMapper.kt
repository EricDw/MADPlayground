package com.example.data.settings.mapper

import com.example.core.settings.models.Settings
import com.example.data.settings.models.*

interface SettingsDataMapper {

    fun mapToCore(settingsData: SettingsData): Settings

    fun mapToData(settings: Settings): SettingsData

}