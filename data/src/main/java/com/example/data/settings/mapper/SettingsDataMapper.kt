package com.example.data.settings.mapper

import com.example.core.settings.models.Settings
import com.example.data.settings.models.IconographyType
import com.example.data.settings.models.NavigationLabelVisibility
import com.example.data.settings.models.ShapeType
import com.example.data.settings.models.ThemeType

interface SettingsDataMapper {

    fun mapToCore(themeType: ThemeType): Settings.ThemeType

    fun mapToCore(shapeType: ShapeType): Settings.ShapeType

    fun mapToCore(iconographyType: IconographyType): Settings.IconographyType

    fun mapToCore(navigationLabelVisibility: NavigationLabelVisibility): Settings.NavigationLabelVisibility

    fun mapToData(themeType: Settings.ThemeType): ThemeType

    fun mapToData(shapeType: Settings.ShapeType): ShapeType

    fun mapToData(iconographyType: Settings.IconographyType): IconographyType

    fun mapToData(navigationLabelVisibility: Settings.NavigationLabelVisibility): NavigationLabelVisibility

}