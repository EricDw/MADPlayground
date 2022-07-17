package com.example.madplayground.ui.container

import androidx.compose.runtime.*
import com.example.madplayground.features.settings.apis.Settings
import com.example.madplayground.ui.container.api.ContentContainer

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

    override var alwaysShowNavigationLabels: Boolean by mutableStateOf(false)

}

@Composable
fun rememberContentContainerState(): ContentContainerState = remember {
    ContentContainerState()
}