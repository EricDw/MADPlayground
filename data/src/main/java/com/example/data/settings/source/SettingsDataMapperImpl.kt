package com.example.data.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.source.SettingsImpl
import com.example.data.settings.mapper.SettingsDataMapper
import com.example.data.settings.models.SettingsData

class SettingsDataMapperImpl : SettingsDataMapper {

    override fun mapToCore(
        settingsData: SettingsData,
    ): Settings {
        return with(settingsData) {
            SettingsImpl(
                themeType = themeType?.let(Settings.ThemeType::valueOf),
                shapeType = shapeType?.let(Settings.ShapeType::valueOf),
                iconographyType = iconographyType?.let(Settings.IconographyType::valueOf),
                navigationLabelVisibility = navigationLabelVisibility?.let(Settings.NavigationLabelVisibility::valueOf),
            )
        }
    }

    override fun mapToData(settings: Settings): SettingsData {
        return with(settings) {
            SettingsDataImpl(
                themeType = themeType.name,
                shapeType = shapeType.name,
                iconographyType = iconographyType.name,
                navigationLabelVisibility = navigationLabelVisibility.name
            )
        }
    }

}