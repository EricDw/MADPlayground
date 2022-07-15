package com.example.madplayground.ui.container.controller

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.madplayground.ui.config.LocalWindowConfiguration
import com.example.madplayground.ui.container.ContentContainer
import com.example.madplayground.ui.container.api.ContentContainer
import com.example.madplayground.ui.container.viewmodel.ContentContainerViewModel
import com.example.madplayground.ui.logs.LocalLogger
import com.example.madplayground.ui.screens.home.api.HomeScreen
import com.example.madplayground.ui.screens.home.controller.HomeScreenController
import com.example.madplayground.ui.screens.settings.api.SettingsScreen
import com.example.madplayground.ui.screens.settings.controller.SettingsScreenController

@Composable
fun ContentContainerController(
    contentContainerViewModel: ContentContainer.ViewModel = hiltViewModel<ContentContainerViewModel>(),
) {

    val tag = "ContentContainerController"

    val logger = LocalLogger.current

    val windowConfiguration = LocalWindowConfiguration.current

    val contentContainerState by contentContainerViewModel.stateFlow.collectAsState()

    val sideEffect by contentContainerViewModel.sideEffect.collectAsState()

    val navHostController: NavHostController = rememberNavController()

    LaunchedEffect(key1 = sideEffect) {

        logger.logDebug(
            tag = tag,
            message = "Handling: $sideEffect"
        )

        when (sideEffect) {

            is ContentContainer.SideEffect.NavigateToHomeTab     -> {
                navHostController.navigate(
                    ContentContainer.HOME_GRAPH_ROUTE
                ) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(
                        id = navHostController.graph.findStartDestination().id
                    ) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // re-selecting the same item
                    launchSingleTop = true
                    // Restore state when re-selecting a previously selected item
                    restoreState = true
                }
            }

            is ContentContainer.SideEffect.NavigateToSettingsTab -> {
                navHostController.navigate(
                    ContentContainer.SETTINGS_GRAPH_ROUTE,
                ) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(
                        id = navHostController.graph.findStartDestination().id
                    ) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // re-selecting the same item
                    launchSingleTop = true
                    // Restore state when re-selecting a previously selected item
                    restoreState = true
                }
            }

            is ContentContainer.SideEffect.NavigateBack          -> {
                navHostController.popBackStack()
            }

            null                                                 -> {
                /* no-op */
            }

        }
    }

    LaunchedEffect(key1 = windowConfiguration) {
        contentContainerViewModel.eventHandler(
            ContentContainer.Event.WindowConfigurationChanged(windowConfiguration)
        )
    }

    ContentContainer(
        state = contentContainerState,
        eventHandler = contentContainerViewModel.eventHandler,
        modifier = Modifier.fillMaxSize(),
    ) { rootPadding ->

        val screenModifier = Modifier
            .fillMaxSize()
            .padding(rootPadding)

        NavHost(
            navController = navHostController,
            startDestination = ContentContainer.HOME_GRAPH_ROUTE,
        ) {

            navigation(
                startDestination = HomeScreen.ROUTE,
                route = ContentContainer.HOME_GRAPH_ROUTE
            ) {

                composable(
                    route = HomeScreen.ROUTE
                ) {

                    HomeScreenController(
                        modifier = screenModifier,
                        contentContainerEventHandler = contentContainerViewModel.eventHandler
                    )

                }

            }

            navigation(
                startDestination = SettingsScreen.ROUTE,
                route = ContentContainer.SETTINGS_GRAPH_ROUTE
            ) {

                composable(
                    route = SettingsScreen.ROUTE
                ) {

                    SettingsScreenController(
                        modifier = screenModifier,
                        contentContainerEventHandler = contentContainerViewModel.eventHandler,
                    )

                }

            }

        }

    }

}