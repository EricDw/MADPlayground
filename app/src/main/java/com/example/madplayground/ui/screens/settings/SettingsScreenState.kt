package com.example.madplayground.ui.screens.settings

import androidx.compose.runtime.*
import com.example.madplayground.models.apis.Settings
import com.example.madplayground.ui.screens.settings.api.SettingsScreen

class SettingsScreenState : SettingsScreen.State {

    override var themeType: Settings.ThemeType by mutableStateOf(
        Settings.ThemeType.SYSTEM
    )

    override var iconType: Settings.IconographyType by mutableStateOf(
        Settings.IconographyType.DEFAULT
    )

    override var shapeType: Settings.ShapeType by mutableStateOf(
        Settings.ShapeType.ROUNDED
    )

}

@Composable
fun rememberSettingsScreenState(
    initializer: SettingsScreenState.() -> Unit = {},
): SettingsScreenState = remember {
    SettingsScreenState().apply(initializer)
}