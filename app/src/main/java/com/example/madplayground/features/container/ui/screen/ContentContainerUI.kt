package com.example.madplayground.features.container.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madplayground.R
import com.example.madplayground.features.container.api.ContentContainer
import com.example.madplayground.features.container.ui.components.ContextualFloatingActionButton
import com.example.madplayground.features.container.ui.components.ContextualNavigationRail
import com.example.madplayground.features.container.ui.components.ContextualTopAppBar
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.settings.apis.Settings
import com.example.madplayground.features.theme.ThemeController
import com.example.madplayground.features.theme.models.LocalIconography

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

    var isNavigationButtonEnabled by remember {
        mutableStateOf(false)
    }

    val isTopNavigationIconVisible =
        !showNavigationRail && state.screenContext != ContentContainer.ScreenContext.HOME

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

    LaunchedEffect(key1 = state.screenContext) {
        when (
            state.screenContext
        ) {

            ContentContainer.ScreenContext.HOME       -> {
                isNavigationButtonEnabled = false
            }

            ContentContainer.ScreenContext.SETTINGS   -> {
                isNavigationButtonEnabled = true
            }

            ContentContainer.ScreenContext.QUOTE_FORM -> {
                isNavigationButtonEnabled = true
            }

        }

    }

}

@Composable
private fun TopBar(
    isNavigationButtonEnabled: Boolean,
    showNavigationIcon: Boolean,
    eventHandler: Message.Handler<ContentContainer.Event>,
    state: ContentContainer.State,
) {

    val elevation = when (state.screenContext) {
        ContentContainer.ScreenContext.QUOTE_FORM -> {
            0.dp
        }

        else                                      -> {
            1.dp
        }
    }

    val backgroundColor = when (state.screenContext) {

        ContentContainer.ScreenContext.QUOTE_FORM -> {
            MaterialTheme.colors.primary
        }

        else                                      -> {
            MaterialTheme.colors.primarySurface
        }

    }

    TopAppBar(
        navigationIcon = if (!showNavigationIcon) {
            null
        } else {
            {
                NavigationIcon(isNavigationButtonEnabled, eventHandler, state)
            }
        },
        title = {

            val titleId = when (state.screenContext) {

                ContentContainer.ScreenContext.HOME       -> {
                    R.string.title_home
                }

                ContentContainer.ScreenContext.SETTINGS   -> {
                    R.string.title_settings
                }
                ContentContainer.ScreenContext.QUOTE_FORM -> {
                    R.string.title_compose_quote
                }
            }

            Text(
                text = stringResource(id = titleId),
            )
        },
        actions = {
        },
        elevation = elevation,
        backgroundColor = backgroundColor
    )
}

@Composable
private fun NavigationIcon(
    isNavigationButtonEnabled: Boolean,
    eventHandler: Message.Handler<ContentContainer.Event>,
    state: ContentContainer.State,
) {
    AnimatedVisibility(
        visible = isNavigationButtonEnabled,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally(),
    ) {

        IconButton(
            onClick = {
                eventHandler(
                    ContentContainer.Event.NavigationButtonClicked
                )
            }
        ) {

            val icon = when (state.screenContext) {

                ContentContainer.ScreenContext.HOME       -> {
                    LocalIconography.current.menuIcon

                }

                ContentContainer.ScreenContext.SETTINGS   -> {
                    LocalIconography.current.backIcon
                }

                ContentContainer.ScreenContext.QUOTE_FORM -> {
                    LocalIconography.current.backIcon
                }
            }

            Icon(
                imageVector = icon,
                contentDescription = ""
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {

    ContentContainer(
        modifier = Modifier.fillMaxSize(),
    ) { scaffoldPadding ->

        Box(
            modifier = Modifier
                .padding(scaffoldPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Hello World",
            )
        }

    }

}