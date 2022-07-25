package com.example.madplayground.cache.settings.fakes

import com.example.madplayground.cache.settings.models.SettingsCache
import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.settings.models.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FakeSettingsCache(
    logs: Logs
) : SettingsCache, Logs by logs {

    private val tag = this::class.simpleName

    override val themeType =
        MutableStateFlow(Settings.ThemeType.SYSTEM)

    override val iconographyType: StateFlow<Settings.IconographyType> =
        MutableStateFlow(Settings.IconographyType.TWO_TONE)

    override val shapeType: StateFlow<Settings.ShapeType> =
        MutableStateFlow(Settings.ShapeType.CUT)

    override val navigationLabelVisibility: StateFlow<Settings.NavigationLabelVisibility> =
        MutableStateFlow(Settings.NavigationLabelVisibility.NEVER)

    override suspend fun setThemeType(newThemeType: Settings.ThemeType) {
        logDebug(
            tag = tag,
            message = "Changing ThemType to $newThemeType"
        )
        themeType.update { newThemeType }
    }

    override suspend fun setIconographyType(newIconographyType: Settings.IconographyType) {
        TODO("Not yet implemented")
    }

    override suspend fun setShapeType(newShapeType: Settings.ShapeType) {
        TODO("Not yet implemented")
    }

    override suspend fun setNavigationLabelVisibility(newVisibility: Settings.NavigationLabelVisibility) {
        TODO("Not yet implemented")
    }
}