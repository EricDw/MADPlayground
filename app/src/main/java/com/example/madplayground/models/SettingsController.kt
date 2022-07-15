package com.example.madplayground.models

import com.example.madplayground.models.apis.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsController : Settings {

    private val _themeType = MutableStateFlow(
        Settings.ThemeType.SYSTEM
    )

    private val _iconographyType = MutableStateFlow(
        Settings.IconographyType.DEFAULT
    )

    private val _shapeType = MutableStateFlow(
        Settings.ShapeType.ROUNDED
    )

    override val themeType: StateFlow<Settings.ThemeType> =
        _themeType.asStateFlow()

    override val iconographyType: StateFlow<Settings.IconographyType> =
        _iconographyType.asStateFlow()

    override val shapeType: StateFlow<Settings.ShapeType> =
        _shapeType.asStateFlow()

    override suspend fun setThemeType(
        newThemeType: Settings.ThemeType,
    ) {
        _themeType.value = newThemeType
    }

    override suspend fun setIconographyType(
        newIconographyType: Settings.IconographyType
    ) {
        _iconographyType.value = newIconographyType
    }

    override suspend fun setShapeType(
        newShapeType: Settings.ShapeType
    ) {
        _shapeType.value = newShapeType
    }

}