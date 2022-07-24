package com.example.madplayground.ui.container.source

import androidx.compose.runtime.*
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.container.models.ContentContainer

class ContentContainerState : ContentContainer.State {

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
fun rememberContentContainerState(
    initializer: ContentContainerState.() -> Unit = { /* no-op */ },
): ContentContainerState = remember {
    ContentContainerState().apply(initializer)
}