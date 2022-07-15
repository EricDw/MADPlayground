package com.example.madplayground.ui.container

import androidx.compose.runtime.*
import com.example.madplayground.models.apis.Settings
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

    override var triggerDrawerToOpen: Boolean by mutableStateOf(false)

    override var showTopAppBar: Boolean by mutableStateOf(true)

    override var showNavigationIcon: Boolean by mutableStateOf(true)

    override var showBottomNavBar: Boolean by mutableStateOf(false)

    override var showNavigationRail: Boolean by mutableStateOf(true)

}

@Composable
fun rememberContentContainerState(): ContentContainerState = remember {
    ContentContainerState()
}