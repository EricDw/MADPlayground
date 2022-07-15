package com.example.madplayground.models.apis

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Settings {

    val themeType: StateFlow<ThemeType>

    val iconographyType: StateFlow<IconographyType>

    val shapeType: StateFlow<ShapeType>

    suspend fun setThemeType(
        newThemeType: ThemeType,
    )

    suspend fun setIconographyType(
        newIconographyType: IconographyType,
    )

    suspend fun setShapeType(
        newShapeType: ShapeType,
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

    interface DataSource {

        suspend fun insertSettings(
            newSettings: Settings,
        )

        suspend fun getSettings(): Settings

        fun getDefaultSettings(): Settings

    }

    interface Repository {

        suspend fun getSettings(): Settings

        suspend fun insertSettings(newSettings: Settings)

        fun getSettingsFlow(): Flow<Settings>

    }

}
