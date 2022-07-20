package com.example.madplayground.ui.settings.screen.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.features.settings.ui.screens.SettingsScreen
import com.example.madplayground.ui.settings.models.SettingsScreen
import com.example.madplayground.ui.settings.source.AndroidSettingsScreenViewModel

@Composable
fun SettingsScreenController(
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
                    SettingsScreen.ViewModel.Action.CycleIconType
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

        }

    }

    SettingsScreen(
        state = state,
        modifier = modifier,
        eventHandler = settingsScreenEventHandler,
    )

}