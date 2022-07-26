package com.example.madplayground.ui.container.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import com.example.madplayground.ui.screens.MomentFormScreen
import com.example.madplayground.ui.screens.Screen
import com.example.madplayground.ui.screens.SettingsScreen
import com.example.madplayground.ui.screens.TimelineScreen
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun ContentContainerNavigationRail(
    contentContainer: ContentContainer,
    modifier: Modifier = Modifier,
    navigationLabelVisibility: Settings.NavigationLabelVisibility = Settings.NavigationLabelVisibility.WHEN_SELECTED,
) {

    var header: (@Composable ColumnScope.() -> Unit)? by remember {
        mutableStateOf(null)
    }

    var isTimelineSelected by remember {
        mutableStateOf(true)
    }

    var isSettingsSelected by remember {
        mutableStateOf(false)
    }

    AnimatedVisibility(
        contentContainer.showNavigationRail,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally()
    ) {
        NavigationRail(
            modifier = modifier,
            header = header,
        ) {

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {

                RailItem(
                    isSelected = isTimelineSelected,
                    imageVector = LocalIconography.current.timelineIcon,
                    descriptionId = R.string.description_timeline_tab,
                    navigationLabelVisibility = navigationLabelVisibility,
                    textId = R.string.title_timeline
                ) {
                    contentContainer.currentScreen.onEvent(
                        ContentContainer.Event.TimelineTabClicked
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

            is TimelineScreen -> {

                header = {
                    ContentContainerFloatingActionButton(
                        contentContainer = contentContainer,
                    )
                }

                isTimelineSelected = true
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

                isTimelineSelected = false
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

                isTimelineSelected = false
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