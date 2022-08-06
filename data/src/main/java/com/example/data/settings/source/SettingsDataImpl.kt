package com.example.data.settings.source

import com.example.data.settings.models.SettingsData

data class SettingsDataImpl(
    override val themeType: String?,
    override val shapeType: String?,
    override val iconographyType: String?,
    override val navigationLabelVisibility: String?,
) : SettingsData