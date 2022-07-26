package com.example.madplayground.ui.container.source

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.container.models.ContentContainer
import kotlinx.coroutines.flow.MutableStateFlow

class ContentContainerState : ContentContainer.State {

    override val themeType = MutableStateFlow(
        Settings.ThemeType.SYSTEM
    )

    override val iconographyType = MutableStateFlow(
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
fun rememberContentContainerState(
    initializer: ContentContainerState.() -> Unit = { /* no-op */ },
): ContentContainerState = remember {
    ContentContainerState().apply(initializer)
}