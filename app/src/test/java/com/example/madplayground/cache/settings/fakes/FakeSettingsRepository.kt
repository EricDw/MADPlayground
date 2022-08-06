package com.example.madplayground.cache.settings.fakes

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeSettingsRepository(
    logs: com.example.common.logs.models.Logs
) : SettingsRepository, com.example.common.logs.models.Logs by logs {

    private val tag = this::class.simpleName

    override val themeType =
        MutableStateFlow(Settings.ThemeType.SYSTEM)

    override val iconographyType =
        MutableStateFlow(Settings.IconographyType.TWO_TONE)

    override val shapeType =
        MutableStateFlow(Settings.ShapeType.CUT)

    override val navigationLabelVisibility =
        MutableStateFlow(Settings.NavigationLabelVisibility.NEVER)

    override suspend fun setThemeType(newThemeType: Settings.ThemeType) {
        logDebug(
            tag = tag,
            message = "Changing ThemType to $newThemeType"
        )
        themeType.update { newThemeType }
    }

    override suspend fun setIconographyType(newIconographyType: Settings.IconographyType) {
        logDebug(
            tag = tag,
            message = "Changing IconographyType to $newIconographyType"
        )
        iconographyType.update { newIconographyType }
    }

    override suspend fun setShapeType(newShapeType: Settings.ShapeType) {
        logDebug(
            tag = tag,
            message = "Changing ShapeType to $newShapeType"
        )
        shapeType.update { newShapeType }
    }

    override suspend fun setNavigationLabelVisibility(newVisibility: Settings.NavigationLabelVisibility) {
        logDebug(
            tag = tag,
            message = "Changing NavigationLabelVisibility to $newVisibility"
        )
        navigationLabelVisibility.update { newVisibility }
    }

    override fun retrieveSettingsFlow(): Flow<Settings> {
        TODO("Not yet implemented")
    }

}