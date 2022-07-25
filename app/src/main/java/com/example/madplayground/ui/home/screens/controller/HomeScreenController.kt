package com.example.madplayground.ui.home.screens.controller

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.util.navigateToGraph
import com.example.madplayground.ui.home.screens.HomeScreen
import com.example.madplayground.ui.screen.HomeScreen
import com.example.madplayground.ui.home.source.AndroidHomeScreenViewModel
import com.example.madplayground.ui.screen.MomentFormScreen

@Composable
fun HomeScreenController(
    contentContainer: ContentContainer.Controller,
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreen.ViewModel = hiltViewModel<AndroidHomeScreenViewModel>(),
) {

    val state by homeScreenViewModel.state.collectAsState()

    val screenInterface = remember {

        object : HomeScreen {

            override fun onEvent(event: ContentContainer.Event) {
                when (event) {

                    ContentContainer.Event.FABClicked              -> {
                        contentContainer.navHostController.navigate(
                            MomentFormScreen.ROUTE
                        )
                    }

                    ContentContainer.Event.HomeTabClicked          -> {
                        /* no-op */
                    }

                    ContentContainer.Event.NavigationButtonClicked -> {
                        TODO()
                    }

                    ContentContainer.Event.SettingsTabClicked      -> {
                        contentContainer.navHostController.navigateToGraph(
                            ContentContainer.SETTINGS_GRAPH_ROUTE
                        )
                    }

                }

            }

        }

    }

    HomeScreen(
        modifier = modifier,
        state = state
    )

    LaunchedEffect(key1 = screenInterface) {
        contentContainer.currentScreen = screenInterface
    }

}