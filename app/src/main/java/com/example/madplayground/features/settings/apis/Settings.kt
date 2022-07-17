package com.example.madplayground.features.settings.apis

import kotlinx.coroutines.flow.StateFlow

interface Settings {

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

    enum class ThemeType {
        LIGHT, DARK, SYSTEM;
    }

    enum class ShapeType {
        ROUNDED, CUT;
    }

    enum class IconographyType {
        DEFAULT, SHARP, OUTLINED, ROUNDED, TWO_TONE;
    }

    enum class NavigationLabelVisibility {
        NEVER, ALWAYS, WHEN_SELECTED;
    }

}
