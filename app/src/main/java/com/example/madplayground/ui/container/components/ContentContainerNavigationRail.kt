package com.example.madplayground.ui.container.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.madplayground.R
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.screen.HomeScreen
import com.example.madplayground.ui.screen.MomentFormScreen
import com.example.madplayground.ui.screen.Screen
import com.example.madplayground.ui.screen.SettingsScreen
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun ContentContainerNavigationRail(
    contentContainer: ContentContainer,
    modifier: Modifier = Modifier,
    navigationLabelVisibility: Settings.NavigationLabelVisibility = Settings.NavigationLabelVisibility.WHEN_SELECTED,
    isVisible: Boolean = true,
) {

    var header: (@Composable ColumnScope.() -> Unit)? by remember {
        mutableStateOf(null)
    }

    var isHomeSelected by remember {
        mutableStateOf(true)
    }

    var isSettingsSelected by remember {
        mutableStateOf(false)
    }

    if (isVisible) {
        NavigationRail(
            modifier = modifier,
            header = header,
        ) {

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {

                RailItem(
                    isSelected = isHomeSelected,
                    imageVector = LocalIconography.current.homeIcon,
                    descriptionId = R.string.description_home_tab,
                    navigationLabelVisibility = navigationLabelVisibility,
                    textId = R.string.title_home
                ) {
                    contentContainer.currentScreen.onEvent(
                        ContentContainer.Event.HomeTabClicked
                    )
                }

                RailItem(
                    isSelected = isSettingsSelected,
                    imageVector = LocalIconography.current.settingsIcon,
                    descriptionId = R.string.description_settings_tab,
                    navigationLabelVisibility = navigationLabelVisibility,
                    textId = R.string.title_settings
                ) {
                    contentContainer.currentScreen.onEvent(
                        ContentContainer.Event.SettingsTabClicked
                    )
                }

            }

        }
    }

    LaunchedEffect(
        key1 = contentContainer.currentScreen
    ) {

        when (contentContainer.currentScreen) {

            is Screen.EMPTY     -> {
                /* no-op */
            }

            is HomeScreen       -> {

                header = {
                    ContentContainerFloatingActionButton(
                        contentContainer = contentContainer,
                    )
                }

                isHomeSelected = true
                isSettingsSelected = false

            }

            is SettingsScreen   -> {

                header = {
                    NavigationIcon(
                        icon = LocalIconography.current.backIcon,
                        eventHandler = contentContainer.currentScreen::onEvent,
                        descriptionId = R.string.description_go_back
                    )
                }

                isHomeSelected = false
                isSettingsSelected = true

            }

            is MomentFormScreen -> {

                header = {
                    NavigationIcon(
                        icon = LocalIconography.current.backIcon,
                        eventHandler = contentContainer.currentScreen::onEvent,
                        descriptionId = R.string.description_go_back
                    )
                }

                isHomeSelected = false
                isSettingsSelected = false

            }

        }

    }

}

@Composable
private fun NavigationIcon(
    icon: ImageVector,
    descriptionId: Int? = null,
    eventHandler: (ContentContainer.Event) -> Unit,
) {

    IconButton(
        onClick = {
            eventHandler(
                ContentContainer.Event.NavigationButtonClicked
            )
        }
    ) {

        Icon(
            imageVector = icon,
            contentDescription = descriptionId?.let {
                stringResource(id = it)
            }
        )

    }

}

@Composable
private fun RailItem(
    imageVector: ImageVector,
    @StringRes
    descriptionId: Int,
    isSelected: Boolean,
    navigationLabelVisibility: Settings.NavigationLabelVisibility,
    textId: Int,
    onClick: () -> Unit,
) {
    NavigationRailItem(
        selected = isSelected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = imageVector,
                contentDescription = stringResource(id = descriptionId)
            )
        },
        label = if (navigationLabelVisibility == Settings.NavigationLabelVisibility.NEVER) {
            null
        } else {
            {
                Text(
                    text = stringResource(id = textId)
                )
            }
        },
        alwaysShowLabel = navigationLabelVisibility == Settings.NavigationLabelVisibility.ALWAYS
    )
}