package com.example.madplayground.ui.container.source

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.util.navigateToGraph
import com.example.madplayground.ui.timeline.screens.controller.TimelineScreenController
import com.example.madplayground.ui.moments.screens.controller.MomentFormScreenController
import com.example.madplayground.ui.moments.models.MomentFormScreen
import com.example.madplayground.ui.settings.models.SettingsScreen
import com.example.madplayground.ui.timeline.models.TimelineScreen
import com.example.madplayground.ui.settings.source.SettingsScreenController

@Composable
fun ContentContainerController(
    contentContainerViewModel: ContentContainer.ViewModel = hiltViewModel<AndroidContentContainerViewModel>(),
) {

    val navHostController: NavHostController = rememberNavController()

    val controllerScope = remember {
        ContentContainerControllerImpl(
            contentContainerState = contentContainerViewModel.state,
            navHostController = navHostController
        )
    }

    controllerScope.ContentContainer(
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
                startDestination = TimelineScreen.ROUTE,
                route = ContentContainer.HOME_GRAPH_ROUTE
            ) {

                composable(route = TimelineScreen.ROUTE) {

                    controllerScope.TimelineScreenController(
                        modifier = screenModifier
                    )

                }

                composable(route = MomentFormScreen.ROUTE) {

                    controllerScope.MomentFormScreenController(
                        modifier = screenModifier,
                    )

                }

            }

            navigation(startDestination = SettingsScreen.ROUTE,
                       route = ContentContainer.SETTINGS_GRAPH_ROUTE) {

                composable(route = SettingsScreen.ROUTE) {

                    controllerScope.SettingsScreenController(
                        modifier = screenModifier,
                    )

                }

            }

        }

    }

}

private class ContentContainerControllerImpl(
    contentContainerState: ContentContainer.State,
    override val navHostController: NavHostController,
) : ContentContainer.Controller {

    override val state: ContentContainer.State = contentContainerState

    override var showTopAppBar: Boolean by mutableStateOf(false)

    override var titleId: Int? = null

    override var topAppBarIcon: (@Composable () -> Unit)? by mutableStateOf(null)

    override var topAppBarActions: @Composable RowScope.() -> Unit by mutableStateOf({})

    override var showBottomNavBar: Boolean by mutableStateOf(false)

    override var isTimelineSelected: Boolean by mutableStateOf(true)

    override var isSettingsSelected: Boolean by mutableStateOf(false)

    override var onTimelineClick: () -> Unit by mutableStateOf(
        {
            navHostController.navigateToGraph(
                route = ContentContainer.HOME_GRAPH_ROUTE
            )
        }
    )

    override var onSettingClick: () -> Unit by mutableStateOf(
        {
            navHostController.navigateToGraph(
                route = ContentContainer.SETTINGS_GRAPH_ROUTE)
        }
    )

    override var showNavigationRail: Boolean by mutableStateOf(false)

    override var railHeader: (@Composable ColumnScope.() -> Unit)? by mutableStateOf(null)

    override var showBottomFAB: Boolean by mutableStateOf(false)

    override var bottomFAB: @Composable () -> Unit by mutableStateOf({})

}