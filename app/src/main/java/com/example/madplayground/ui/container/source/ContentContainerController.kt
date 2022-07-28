package com.example.madplayground.ui.container.source

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
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
import com.example.madplayground.ui.moments.models.MomentFormScreen
import com.example.madplayground.ui.moments.source.MomentFormScreenController
import com.example.madplayground.ui.settings.models.SettingsScreen
import com.example.madplayground.ui.settings.source.SettingsScreenController
import com.example.madplayground.ui.timeline.models.TimelineScreen
import com.example.madplayground.ui.timeline.source.TimelineScreenController

@Composable
fun ContentContainerController(
    contentContainerViewModel: ContentContainer.ViewModel = hiltViewModel<AndroidContentContainerViewModel>(),
) {

    val navHostController: NavHostController = rememberNavController()

    val scaffoldState = rememberScaffoldState()

    val controllerScope = remember {
        ContentContainerControllerImpl(
            state = contentContainerViewModel.state,
            navHostController = navHostController,
            containerScaffoldState = scaffoldState
        )
    }

    controllerScope.ContentContainer(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) { rootPadding ->

        val screenModifier = Modifier
            .fillMaxSize()
            .padding(rootPadding)

        NavHost(
            navController = navHostController,
            startDestination = ContentContainer.TIMELINE_GRAPH_ROUTE,
        ) {

            navigation(
                startDestination = TimelineScreen.ROUTE,
                route = ContentContainer.TIMELINE_GRAPH_ROUTE
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
    override val state: ContentContainer.State,
    override val navHostController: NavHostController,
    override val containerScaffoldState: ScaffoldState,
) : ContentContainer.Controller {

    override var showTopAppBar: Boolean by mutableStateOf(false)

    override var titleId: Int? = null

    override var navigationIcon: (@Composable () -> Unit)? by mutableStateOf(null)

    override var topAppBarActions: @Composable RowScope.() -> Unit by mutableStateOf({})

    override var drawerGesturesEnabled: Boolean = false

    override var drawerContent: (@Composable ColumnScope.() -> Unit)? by mutableStateOf(null)

    override var showBottomNavBar: Boolean by mutableStateOf(false)

    override var isTimelineSelected: Boolean by mutableStateOf(true)

    override var isSettingsSelected: Boolean by mutableStateOf(false)

    override var onTimelineClick: () -> Unit by mutableStateOf(
        {
            navHostController.navigateToGraph(
                route = ContentContainer.TIMELINE_GRAPH_ROUTE
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

    override var showFab: Boolean by mutableStateOf(false)

    override var floatingActionButton: @Composable () -> Unit by mutableStateOf({})

    override var fabPosition: FabPosition by mutableStateOf(FabPosition.End)

    override var dockFab: Boolean by mutableStateOf(false)

    override var snackbarHost: @Composable (SnackbarHostState) -> Unit by mutableStateOf(
        { hostState ->
            SnackbarHost(
                hostState = hostState
            )
        }
    )

}