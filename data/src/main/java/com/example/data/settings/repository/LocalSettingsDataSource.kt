package com.example.data.settings.repository

import com.example.data.settings.models.IconographyType
import com.example.data.settings.models.NavigationLabelVisibility
import com.example.data.settings.models.ShapeType
import com.example.data.settings.models.ThemeType
import kotlinx.coroutines.flow.StateFlow

interface LocalSettingsDataSource {

    val themeType: StateFlow<ThemeType>

    val iconographyType: StateFlow<IconographyType>

    val shapeType: StateFlow<ShapeType>

    val navigationLabelVisibility: StateFlow<NavigationLabelVisibility>

    suspend fun setThemeType(
        newThemeType: ThemeType,
    )

    suspend fun setIconographyType(
        newIconographyType: IconographyType,
    )

    suspend fun setShapeType(
        newShapeType: ShapeType,
    )

    suspend fun setNavigationLabelVisibility(
        newVisibility: NavigationLabelVisibility,
    )


}