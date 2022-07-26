package com.example.madplayground.ui.settings.source

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.util.navigateToGraph
import com.example.madplayground.ui.screens.SettingsScreen

@Composable
fun SettingsScreenController(
    contentContainer: ContentContainer.Controller,
    modifier: Modifier = Modifier,
    settingsScreenViewModel: SettingsScreen.ViewModel = hiltViewModel<AndroidSettingsScreenViewModel>(),
) {

    val state = settingsScreenViewModel.state

    val settingsScreenEventHandler: (SettingsScreen.Event) -> Unit = { theEvent ->

        when (theEvent) {

            SettingsScreen.Event.ThemeTypeClicked       -> {

                settingsScreenViewModel.commandHandler(
                    SettingsScreen.ViewModel.Command.CycleThemeType
                )

            }

            SettingsScreen.Event.IconTypeClicked        -> {

                settingsScreenViewModel.commandHandler(
                    SettingsScreen.ViewModel.Command.CycleIconographyType
                )

            }

            SettingsScreen.Event.ShapeTypeClicked       -> {

                settingsScreenViewModel.commandHandler(
                    SettingsScreen.ViewModel.Command.CycleShapeType
                )

            }

            SettingsScreen.Event.LabelVisibilityClicked -> {

                settingsScreenViewModel.commandHandler(
                    SettingsScreen.ViewModel.Command.CycleLabelVisibility
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

                    ContentContainer.Event.TimelineTabClicked      -> {
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
        contentContainer.currentScreen = screenInterface
    }

}