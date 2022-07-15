package com.example.madplayground.ui.screens.settings.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.messages.api.Message
import com.example.madplayground.ui.container.api.ContentContainer
import com.example.madplayground.ui.screens.settings.SettingsScreen
import com.example.madplayground.ui.screens.settings.api.SettingsScreen
import com.example.madplayground.ui.screens.settings.viewmodel.SettingsScreenViewModel

@Composable
fun SettingsScreenController(
    modifier: Modifier = Modifier,
    contentContainerEventHandler: Message.Handler<ContentContainer.Event> = Message.Handler { /* no-op */ },
    settingsScreenViewModel: SettingsScreen.ViewModel = hiltViewModel<SettingsScreenViewModel>(),
) {

    val state by settingsScreenViewModel.stateFlow.collectAsState()

    val settingsScreenEventHandler = Message.Handler<SettingsScreen.Event> { theEvent ->

        when (theEvent) {

            SettingsScreen.Event.Started          -> {

                contentContainerEventHandler(
                    ContentContainer.Event.SettingsScreenStarted
                )

            }

            SettingsScreen.Event.ThemeTypeClicked -> {

                settingsScreenViewModel.eventHandler(theEvent)

            }

            SettingsScreen.Event.IconTypeClicked  -> {
                settingsScreenViewModel.eventHandler(theEvent)
            }

            SettingsScreen.Event.ShapeTypeClicked -> {
                settingsScreenViewModel.eventHandler(theEvent)
            }

        }

    }

    SettingsScreen(
        state = state,
        modifier = modifier,
        eventHandler = settingsScreenEventHandler,
    )

}