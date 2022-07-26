package com.example.madplayground.ui.settings.source

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.screens.SettingsScreen
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