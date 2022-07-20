package com.example.madplayground.features.container.ui.screen

import androidx.compose.runtime.*
import com.example.madplayground.features.settings.apis.Settings
import com.example.madplayground.features.container.api.ContentContainer

class ContentContainerState : ContentContainer.State {

    override var screenContext: ContentContainer.ScreenContext by mutableStateOf(
        ContentContainer.ScreenContext.HOME
    )

    override var themeType: Settings.ThemeType by mutableStateOf(
        Settings.ThemeType.SYSTEM
    )

    override var iconographyType: Settings.IconographyType by mutableStateOf(
        Settings.IconographyType.DEFAULT
    )

    override var shapeType: Settings.ShapeType by mutableStateOf(
        Settings.ShapeType.ROUNDED
    )

    override var navigationLabelVisibility: Settings.NavigationLabelVisibility by mutableStateOf(
        Settings.NavigationLabelVisibility.WHEN_SELECTED
    )

}

@Composable
fun rememberContentContainerState(): ContentContainerState = remember {
    ContentContainerState()
}