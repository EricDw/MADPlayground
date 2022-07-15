package com.example.madplayground.ui.container

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.madplayground.R
import com.example.madplayground.messages.api.Message
import com.example.madplayground.ui.container.api.ContentContainer
import com.example.madplayground.ui.theme.LocalIconography
import com.example.madplayground.ui.theme.m2.AppTheme

@Composable
fun ContentContainer(
    modifier: Modifier = Modifier,
    state: ContentContainer.State = rememberContentContainerState(),
    eventHandler: Message.Handler<ContentContainer.Event> = Message.Handler { /* no-op */ },
    content: @Composable (scaffoldPadding: PaddingValues) -> Unit,
) {

    val isDrawerUnlocked by remember {
        derivedStateOf {
            when (state.screenContext) {
                ContentContainer.ScreenContext.HOME     -> {
                    true
                }
                ContentContainer.ScreenContext.SETTINGS -> {
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
                eventHandler(
                    ContentContainer.Event.DrawerClosed
                )
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

    AppTheme(
        themeType = state.themeType,
        iconographyType = state.iconographyType,
        shapeType = state.shapeType,
    ) {

        val isNavigationButtonEnabled = when (state.screenContext) {

            ContentContainer.ScreenContext.HOME     -> {
                true
            }

            ContentContainer.ScreenContext.SETTINGS -> {
                true
            }

        }

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = modifier,
            content = content,
            topBar = {
                TopBar(isNavigationButtonEnabled, eventHandler, state)
//                HeaderB(isDrawerEnabled, eventHandler, state)
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
                        }
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
                        }
                    )
                }
            },
            floatingActionButton = {
              FloatingActionButton(onClick = { /*TODO*/ }) {
                  Icon(
                      imageVector = LocalIconography.current.editIcon,
                      contentDescription = null
                  )
              }
            },
            drawerGesturesEnabled = isDrawerUnlocked
        )
    }

    LaunchedEffect(key1 = state.triggerDrawerToOpen) {
        if (state.triggerDrawerToOpen)
            drawerState.open()
    }

}

@Composable
private fun TopBar(
    isNavigationButtonEnabled: Boolean,
    eventHandler: Message.Handler<ContentContainer.Event>,
    state: ContentContainer.State,
) {
    TopAppBar(
        navigationIcon = {

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