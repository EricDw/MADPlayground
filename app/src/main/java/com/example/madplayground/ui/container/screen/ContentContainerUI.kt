package com.example.madplayground.ui.container.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.madplayground.R
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.features.theme.ThemeController
import com.example.madplayground.ui.container.components.ContextualFloatingActionButton
import com.example.madplayground.ui.container.components.ContextualNavigationRail
import com.example.madplayground.ui.container.components.ContextualTopAppBar
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.source.rememberContentContainerState
import com.example.madplayground.ui.container.util.ScreenContextPreviewParameterProvider
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun ContentContainer(
    modifier: Modifier = Modifier,
    state: ContentContainer.State = rememberContentContainerState(),
    showTopAppBar: Boolean = true,
    showBottomNavBar: Boolean = true,
    showNavigationRail: Boolean = false,
    showBottomFAB: Boolean = true,
    eventHandler: Message.Handler<ContentContainer.Event> = Message.Handler { /* no-op */ },
    content: @Composable (scaffoldPadding: PaddingValues) -> Unit,
) {

    val isDrawerUnlocked by remember {

        derivedStateOf {
            when (state.screenContext) {

                ContentContainer.ScreenContext.HOME       -> {
                    false
                }

                ContentContainer.ScreenContext.SETTINGS   -> {
                    false
                }

                ContentContainer.ScreenContext.QUOTE_FORM -> {
                    false
                }

            }

        }

    }

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    ) { drawerValue ->

        when (drawerValue) {

            DrawerValue.Closed -> {
                true
            }

            DrawerValue.Open   -> {

                isDrawerUnlocked

            }

        }

    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scaffoldState: ScaffoldState =
        rememberScaffoldState(
            drawerState = drawerState,
            snackbarHostState = snackbarHostState
        )

    ThemeController(
        themeType = state.themeType,
        iconographyType = state.iconographyType,
        shapeType = state.shapeType,
    ) {

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = modifier,
            topBar = {

                ContextualTopAppBar(
                    screenContext = state.screenContext,
                    eventHandler = eventHandler,
                    isVisible = showTopAppBar
                )

            },
            bottomBar = {

                if (showBottomNavBar) {

                    BottomNavigation {

                        BottomNavigationItem(
                            selected = state.screenContext == ContentContainer.ScreenContext.HOME,
                            onClick = {
                                eventHandler(
                                    ContentContainer.Event.HomeTabClicked
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = LocalIconography.current.homeIcon,
                                    contentDescription = "Home Tab"
                                )
                            },
                            label = if (state.navigationLabelVisibility == Settings.NavigationLabelVisibility.NEVER) {
                                null
                            } else {
                                {
                                    Text(
                                        text = stringResource(id = R.string.title_home)
                                    )
                                }
                            },
                            alwaysShowLabel = state.navigationLabelVisibility == Settings.NavigationLabelVisibility.ALWAYS
                        )

                        BottomNavigationItem(
                            selected = state.screenContext == ContentContainer.ScreenContext.SETTINGS,
                            onClick = {
                                eventHandler(
                                    ContentContainer.Event.SettingsTabClicked
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = LocalIconography.current.settingsIcon,
                                    contentDescription = "Settings Tab"
                                )
                            },
                            label = if (state.navigationLabelVisibility == Settings.NavigationLabelVisibility.NEVER) {
                                null
                            } else {
                                {
                                    Text(
                                        text = stringResource(id = R.string.title_settings)
                                    )
                                }
                            },
                            alwaysShowLabel = state.navigationLabelVisibility == Settings.NavigationLabelVisibility.ALWAYS
                        )
                    }

                }

            },
            snackbarHost = { _ ->
                /* no-op */
            },
            floatingActionButton = {

                ContextualFloatingActionButton(
                    screenContext = state.screenContext,
                    eventHandler = eventHandler,
                    isVisible = showBottomFAB
                )

            },
            drawerContent = null,
            drawerGesturesEnabled = isDrawerUnlocked,
        ) { scaffoldPadding ->

            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                ContextualNavigationRail(
                    screenContext = state.screenContext,
                    eventHandler = eventHandler,
                    isVisible = showNavigationRail,
                    navigationLabelVisibility = state.navigationLabelVisibility
                )

                content(
                    scaffoldPadding
                )

            }

        }

    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ContentContainerPreview(
    @PreviewParameter(ScreenContextPreviewParameterProvider::class)
    screenContext: ContentContainer.ScreenContext,
) {

    val state = rememberContentContainerState {
        this.screenContext = screenContext
    }

    ContentContainer(
        modifier = Modifier.fillMaxSize(),
        state = state,
    ) { scaffoldPadding ->

        Box(
            modifier = Modifier
                .padding(scaffoldPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = screenContext.name,
            )
        }

    }

}