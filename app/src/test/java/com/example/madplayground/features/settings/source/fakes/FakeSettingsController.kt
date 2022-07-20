package com.example.madplayground.features.settings.source.fakes

import com.example.madplayground.domain.settings.models.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeSettingsController : Settings {

    override val themeType =
        MutableStateFlow(
            Settings.ThemeType.SYSTEM
        )

    override val iconographyType =
        MutableStateFlow(
            Settings.IconographyType.DEFAULT
        )

    override val shapeType =
        MutableStateFlow(
            Settings.ShapeType.ROUNDED
        )

    override val navigationLabelVisibility =
        MutableStateFlow(
            Settings.NavigationLabelVisibility.WHEN_SELECTED
        )

    override suspend fun setThemeType(newThemeType: Settings.ThemeType) {
        themeType.update {
            newThemeType
        }
    }

    override suspend fun setIconographyType(newIconographyType: Settings.IconographyType) {
        TODO("Not yet implemented")
    }

    override suspend fun setShapeType(newShapeType: Settings.ShapeType) {
        TODO("Not yet implemented")
    }

    override suspend fun setNavigationLabelVisibility(newVisibility: Settings.NavigationLabelVisibility) {
        navigationLabelVisibility.update {
            newVisibility
        }
    }
}