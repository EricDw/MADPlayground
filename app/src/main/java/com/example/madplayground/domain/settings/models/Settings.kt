package com.example.madplayground.domain.settings.models

interface Settings {

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
