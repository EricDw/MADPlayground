package com.example.madplayground.ui.screens.home.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.messages.api.Message
import com.example.madplayground.ui.container.api.ContentContainer
import com.example.madplayground.ui.screens.home.HomeScreen
import com.example.madplayground.ui.screens.home.api.HomeScreen
import com.example.madplayground.ui.screens.home.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreenController(
    modifier: Modifier = Modifier,
    contentContainerEventHandler: Message.Handler<ContentContainer.Event> = Message.Handler { /* no-op */ },
    homeScreenViewModel: HomeScreen.ViewModel = hiltViewModel<HomeScreenViewModel>(),
) {

    val state by homeScreenViewModel.state.collectAsState()

    val homeScreenEventHandler = HomeScreen.Event.Handler { theEvent ->

        when (theEvent) {
            HomeScreen.Event.HomeScreenStarted -> {
                contentContainerEventHandler(
                    ContentContainer.Event.HomeScreenStarted
                )
            }
        }

    }

    HomeScreen(
        state = state,
        modifier = modifier,
        eventHandler = homeScreenEventHandler
    )

}