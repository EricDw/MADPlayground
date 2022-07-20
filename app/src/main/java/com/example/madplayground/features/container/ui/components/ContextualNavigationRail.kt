package com.example.madplayground.features.container.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.madplayground.R
import com.example.madplayground.features.container.api.ContentContainer
import com.example.madplayground.features.container.ui.util.ScreenContextPreviewParameterProvider
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.settings.apis.Settings
import com.example.madplayground.features.theme.models.LocalIconography

@Composable
fun ContextualNavigationRail(
    screenContext: ContentContainer.ScreenContext,
    eventHandler: Message.Handler<ContentContainer.Event>,
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
            modifier= modifier,
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
                    eventHandler(
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
                    eventHandler(
                        ContentContainer.Event.SettingsTabClicked
                    )
                }

            }

        }
    }

    LaunchedEffect(
        key1 = screenContext
    ) {

        when (screenContext) {

            ContentContainer.ScreenContext.HOME       -> {

                header = {
                    ContextualFloatingActionButton(
                        screenContext = screenContext,
                        eventHandler = eventHandler
                    )
                }

                isHomeSelected = true
                isSettingsSelected = false

            }

            ContentContainer.ScreenContext.SETTINGS   -> {

                header = {
                    NavigationIcon(
                        icon = LocalIconography.current.backIcon,
                        eventHandler = eventHandler,
                        descriptionId = R.string.description_go_back
                    )
                }

                isHomeSelected = false
                isSettingsSelected = true

            }

            ContentContainer.ScreenContext.QUOTE_FORM -> {

                header = {
                    NavigationIcon(
                        icon = LocalIconography.current.backIcon,
                        eventHandler = eventHandler,
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
    eventHandler: Message.Handler<ContentContainer.Event>,
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
    onClick: () -> Unit
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

@Preview
@Composable
private fun ContextualNavigationRailPreview(
    @PreviewParameter(
        ScreenContextPreviewParameterProvider::class
    )
    screenContext: ContentContainer.ScreenContext,
) {

    ContextualNavigationRail(
        screenContext = screenContext,
        eventHandler = { /* no-op */ },
    )

}