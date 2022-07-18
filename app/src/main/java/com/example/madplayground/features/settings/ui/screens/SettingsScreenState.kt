package com.example.madplayground.features.settings.ui.screens

import androidx.compose.runtime.*
import com.example.madplayground.features.settings.apis.Settings
import com.example.madplayground.features.settings.ui.screens.api.SettingsScreen
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsScreenState : SettingsScreen.State {

    override val themeType = MutableStateFlow(
        Settings.ThemeType.SYSTEM
    )

    override val iconType = MutableStateFlow(
        Settings.IconographyType.DEFAULT
    )

    override val shapeType = MutableStateFlow(
        Settings.ShapeType.ROUNDED
    )

    override val navigationLabelVisibility = MutableStateFlow(
        Settings.NavigationLabelVisibility.WHEN_SELECTED
    )

}

@Composable
fun rememberSettingsScreenState(
    initializer: SettingsScreenState.() -> Unit = {},
): SettingsScreenState = remember {
    SettingsScreenState().apply(initializer)
}