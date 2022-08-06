package com.example.cache.settings.mapper

import androidx.datastore.preferences.core.Preferences
import com.example.data.settings.models.*

interface SettingsCacheMapper {

    fun mapToData(preferences: Preferences): SettingsData

}