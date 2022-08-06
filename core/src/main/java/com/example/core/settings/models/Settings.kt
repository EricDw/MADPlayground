package com.example.core.settings.models

interface Settings {

    val themeType: ThemeType

    val shapeType: ShapeType

    val iconographyType: IconographyType

    val navigationLabelVisibility: NavigationLabelVisibility

    fun cycleThemeType()

    fun cycleShapeType()

    fun cycleIconographyType()

    fun cycleNavigationLabelVisibility()

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
