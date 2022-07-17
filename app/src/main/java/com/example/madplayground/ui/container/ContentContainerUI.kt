package com.example.madplayground.ui.container

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.madplayground.R
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.ui.container.api.ContentContainer
import com.example.madplayground.ui.theme.AppTheme
import com.example.madplayground.ui.theme.LocalIconography

@Composable
fun ContentContainer(
    modifier: Modifier = Modifier,
    state: ContentContainer.State = rememberContentContainerState(),
    showTopAppBar: Boolean = true,
    showBottomNavBar: Boolean = true,
    showNavigationRail: Boolean = false,
    eventHandler: Message.Handler<ContentContainer.Event> = Message.Handler { /* no-op */ },
    content: @Composable (scaffoldPadding: PaddingValues) -> Unit,
) {

    val isDrawerUnlocked by remember {
        derivedStateOf {
            when (state.screenContext) {
                ContentContainer.ScreenContext.HOME     -> {
                    false
                }
                ContentContainer.ScreenContext.SETTINGS -> {
                    false
                }
            }
        }
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

    val isNavigationButtonEnabled = when (
        state.screenContext
    ) {

        ContentContainer.ScreenContext.HOME     -> {
            true
        }

        ContentContainer.ScreenContext.SETTINGS -> {
            true
        }

    }

    AppTheme(
        themeType = state.themeType,
        iconographyType = state.iconographyType,
        shapeType = state.shapeType,
    ) {

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = modifier,
            topBar = {
                if (showTopAppBar)
                    TopBar(
                        isNavigationButtonEnabled,
                        isTopNavigationIconVisible,
                        eventHandler,
                        state
                    )
            },
            drawerContent = {

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Text(text = "TODO: Categories")
                    }
                }

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
                            label = {
                                Text(
                                    text = stringResource(id = R.string.title_home)
                                )
                            },
                            alwaysShowLabel = state.alwaysShowNavigationLabels
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
                            label = {
                                Text(
                                    text = stringResource(id = R.string.title_settings)
                                )
                            },
                            alwaysShowLabel = state.alwaysShowNavigationLabels
                        )
                    }

                }

            },
            floatingActionButton = {
                val visible = !showNavigationRail
                AnimatedVisibility(
                    visible = visible,
                ) {

                    FloatingActionButton(
                        onClick = {
                            eventHandler(
                                ContentContainer.Event.FABClicked
                            )
                        }
                    ) {
                        Icon(
                            imageVector = LocalIconography.current.editIcon,
                            contentDescription = null
                        )
                    }

                }
            },
            drawerGesturesEnabled = isDrawerUnlocked,
        ) { scaffoldPadding ->

            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                if (showNavigationRail) {
                    NavigationRail(
                        header = when (state.screenContext) {
                            ContentContainer.ScreenContext.HOME     -> {
                                {
                                    FloatingActionButton(
                                        onClick = {
                                            eventHandler(
                                                ContentContainer.Event.FABClicked
                                            )
                                        }
                                    ) {
                                        Icon(
                                            imageVector = LocalIconography.current.editIcon,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }

                            ContentContainer.ScreenContext.SETTINGS -> {
                                {
                                    NavigationIcon(
                                        isNavigationButtonEnabled = isNavigationButtonEnabled,
                                        eventHandler = eventHandler,
                                        state = state
                                    )
                                }
                            }
                        },
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {

                            NavigationRailItem(
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
                                label = {
                                    Text(
                                        text = stringResource(id = R.string.title_home)
                                    )
                                },
                                alwaysShowLabel = state.alwaysShowNavigationLabels
                            )

                            NavigationRailItem(
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
                                label = {
                                    Text(
                                        text = stringResource(id = R.string.title_settings)
                                    )
                                },
                                alwaysShowLabel = state.alwaysShowNavigationLabels
                            )

                        }

                    }
                }

                content(
                    scaffoldPadding
                )

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

                ContentContainer.ScreenContext.HOME     -> {
                    R.string.title_home
                }

                ContentContainer.ScreenContext.SETTINGS -> {
                    R.string.title_settings
                }

            }

            Text(
                text = stringResource(id = titleId),
            )
        },
        actions = {
        }
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

                ContentContainer.ScreenContext.HOME     -> {
                    LocalIconography.current.menuIcon

                }

                ContentContainer.ScreenContext.SETTINGS -> {
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