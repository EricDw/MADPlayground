package com.example.core.settings.repository

import com.example.core.settings.models.Settings
import kotlinx.coroutines.flow.StateFlow

interface SettingsCache {

    val themeType: StateFlow<Settings.ThemeType>

    val iconographyType: StateFlow<Settings.IconographyType>

    val shapeType: StateFlow<Settings.ShapeType>

    val navigationLabelVisibility: StateFlow<Settings.NavigationLabelVisibility>

    suspend fun setThemeType(
        newThemeType: Settings.ThemeType,
    )

    suspend fun setIconographyType(
        newIconographyType: Settings.IconographyType,
    )

    suspend fun setShapeType(
        newShapeType: Settings.ShapeType,
    )

    suspend fun setNavigationLabelVisibility(
        newVisibility: Settings.NavigationLabelVisibility,
    )


}