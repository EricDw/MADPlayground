package com.example.cache.settings.source

import androidx.datastore.preferences.core.Preferences
import com.example.cache.settings.constants.iconographyKey
import com.example.cache.settings.constants.navigationLabelVisibilityKey
import com.example.cache.settings.constants.shapeKey
import com.example.cache.settings.constants.themeKey
import com.example.cache.settings.mapper.SettingsCacheMapper
import com.example.data.settings.models.SettingsData
import com.example.data.settings.source.SettingsDataImpl

class SettingsCacheMapperImpl : SettingsCacheMapper {

    override fun mapToData(
        preferences: Preferences,
    ): SettingsData {
        return with(preferences) {
            SettingsDataImpl(
                get(themeKey),
                get(shapeKey),
                get(iconographyKey),
                get(navigationLabelVisibilityKey),
            )
        }
    }

}