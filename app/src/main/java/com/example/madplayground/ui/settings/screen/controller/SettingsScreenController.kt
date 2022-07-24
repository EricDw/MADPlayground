package com.example.madplayground.ui.settings.screen.controller

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.util.navigateToGraph
import com.example.madplayground.ui.screen.SettingsScreen
import com.example.madplayground.ui.settings.screen.SettingsScreen
import com.example.madplayground.ui.settings.source.AndroidSettingsScreenViewModel

@Composable
fun SettingsScreenController(
    contentContainer: ContentContainer.Controller,
    modifier: Modifier = Modifier,
    settingsScreenViewModel: SettingsScreen.ViewModel = hiltViewModel<AndroidSettingsScreenViewModel>(),
) {

    val state by settingsScreenViewModel.stateFlow.collectAsState()

    val settingsScreenEventHandler = Message.Handler<SettingsScreen.Event> { theEvent ->

        when (theEvent) {

            SettingsScreen.Event.ThemeTypeClicked       -> {

                settingsScreenViewModel.actionHandler(
                    SettingsScreen.ViewModel.Action.CycleThemeType
                )

            }

            SettingsScreen.Event.IconTypeClicked        -> {

                settingsScreenViewModel.actionHandler(
                    SettingsScreen.ViewModel.Action.CycleIconographyType
                )

            }

            SettingsScreen.Event.ShapeTypeClicked       -> {

                settingsScreenViewModel.actionHandler(
                    SettingsScreen.ViewModel.Action.CycleShapeType
                )

            }

            SettingsScreen.Event.LabelVisibilityClicked -> {

                settingsScreenViewModel.actionHandler(
                    SettingsScreen.ViewModel.Action.CycleLabelVisibility
                )

            }

            SettingsScreen.Event.BackClicked            -> {
                contentContainer.navHostController.popBackStack()
            }

        }

    }

    val screenInterface = remember {

        object : SettingsScreen {

            override fun onEvent(event: ContentContainer.Event) {

                when (event) {

                    ContentContainer.Event.FABClicked              -> {
                        TODO()
                    }

                    ContentContainer.Event.HomeTabClicked          -> {
                        contentContainer.navHostController.navigateToGraph(
                            ContentContainer.HOME_GRAPH_ROUTE
                        )
                    }

                    ContentContainer.Event.NavigationButtonClicked -> {
                        contentContainer.navHostController.popBackStack()
                    }

                    ContentContainer.Event.SettingsTabClicked      -> {
                        /* no-op */
                    }

                }

            }

            override fun onEvent(event: SettingsScreen.Event) {
                settingsScreenEventHandler(event)
            }

        }

    }

    SettingsScreen(
        state = state,
        modifier = modifier,
        eventHandler = settingsScreenEventHandler,
    )

    LaunchedEffect(key1 = screenInterface) {
        contentContainer.setScreen(screenInterface)
    }

}