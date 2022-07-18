package com.example.madplayground.features.container.controller

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.config.CombinedWindowType
import com.example.madplayground.features.config.LocalWindowConfiguration
import com.example.madplayground.features.container.ContentContainer
import com.example.madplayground.features.container.api.ContentContainer
import com.example.madplayground.features.container.viewmodel.ContentContainerViewModel
import com.example.madplayground.ui.logs.LocalLogs
import com.example.madplayground.features.home.api.HomeScreen
import com.example.madplayground.features.home.controller.HomeScreenController
import com.example.madplayground.features.settings.ui.screens.api.SettingsScreen
import com.example.madplayground.features.settings.ui.screens.controller.SettingsScreenController

@Composable
fun ContentContainerController(
    contentContainerViewModel: ContentContainer.ViewModel = hiltViewModel<ContentContainerViewModel>(),
) {

    val tag = "ContentContainerController"

    val logs = LocalLogs.current

    val windowConfiguration = LocalWindowConfiguration.current

    val contentContainerState by contentContainerViewModel.stateFlow.collectAsState()

    var showTopAppBar: Boolean by remember {
        mutableStateOf(true)
    }

    var showBottomNavBar: Boolean by remember {
        mutableStateOf(true)
    }

    var showNavigationRail: Boolean by remember {
        mutableStateOf(false)
    }

    var showScaffoldFAB: Boolean by remember {
        mutableStateOf(false)
    }

    val navHostController: NavHostController = rememberNavController()

    val eventHandler = Message.Handler<ContentContainer.Event> { event ->

        when (event) {

            ContentContainer.Event.FABClicked              -> {
                contentContainerViewModel.actionHandler(
                    ContentContainer.ViewModel.Action.AddNewQuote()
                )
            }

            ContentContainer.Event.HomeTabClicked          -> {

                if (contentContainerState.screenContext != ContentContainer.ScreenContext.HOME)
                    navHostController.navigateToGraph(
                        ContentContainer.HOME_GRAPH_ROUTE
                    )

            }

            ContentContainer.Event.SettingsTabClicked      -> {

                if (contentContainerState.screenContext != ContentContainer.ScreenContext.SETTINGS)
                    navHostController.navigateToGraph(
                        ContentContainer.SETTINGS_GRAPH_ROUTE
                    )

            }

            ContentContainer.Event.NavigationButtonClicked -> {

                when (contentContainerState.screenContext) {

                    ContentContainer.ScreenContext.HOME     -> {
                        /* no-op */
                    }

                    ContentContainer.ScreenContext.SETTINGS -> {
                        navHostController.popBackStack()
                    }

                }

            }

        }

    }

    ContentContainer(
        modifier = Modifier.fillMaxSize(),
        state = contentContainerState,
        showTopAppBar = showTopAppBar,
        showBottomNavBar = showBottomNavBar,
        showNavigationRail = showNavigationRail,
        showScaffoldFAB = showScaffoldFAB,
        eventHandler = eventHandler
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
                        modifier = screenModifier
                    )

                    LaunchedEffect(key1 = true) {
                        contentContainerViewModel.actionHandler(
                            ContentContainer.ViewModel.Action.SwitchContexts(
                                ContentContainer.ScreenContext.HOME
                            )
                        )
                    }

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
                    )

                    LaunchedEffect(key1 = true) {
                        contentContainerViewModel.actionHandler(
                            ContentContainer.ViewModel.Action.SwitchContexts(
                                ContentContainer.ScreenContext.SETTINGS
                            )
                        )
                    }

                }

            }

        }

    }

    LaunchedEffect(key1 = windowConfiguration) {
        when (
            windowConfiguration.combinedWindowType
        ) {

            CombinedWindowType.COMPACT_WIDTH_COMPACT_HEIGHT   -> {

                showTopAppBar = false
                showBottomNavBar = false
                showNavigationRail = false
                showScaffoldFAB = true

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration not handled!"
                )

            }

            CombinedWindowType.COMPACT_WIDTH_MEDIUM_HEIGHT    -> {

                showBottomNavBar = true
                showNavigationRail = false
                showScaffoldFAB = true

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration not handled!"
                )

            }

            CombinedWindowType.COMPACT_WIDTH_EXPANDED_HEIGHT  -> {
                // TODO: Configure State

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration not handled!"
                )

            }

            CombinedWindowType.MEDIUM_WIDTH_COMPACT_HEIGHT    -> {

                showBottomNavBar = false
                showNavigationRail = true
                showScaffoldFAB = false

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration not handled!"
                )

            }

            CombinedWindowType.MEDIUM_WIDTH_MEDIUM_HEIGHT     -> {
                // TODO: Configure State

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration not handled!"
                )

            }

            CombinedWindowType.MEDIUM_WIDTH_EXPANDED_HEIGHT   -> {
                // TODO: Configure State

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration not handled!"
                )

            }

            CombinedWindowType.EXPANDED_WIDTH_COMPACT_HEIGHT  -> {
                // TODO: Configure State

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration not handled!"
                )

            }

            CombinedWindowType.EXPANDED_WIDTH_MEDIUM_HEIGHT   -> {
                // TODO: Configure State

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration not handled!"
                )

            }

            CombinedWindowType.EXPANDED_WIDTH_EXPANDED_HEIGHT -> {
                // TODO: Configure State

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration not handled!"
                )

            }

        }

    }

}

private fun NavHostController.navigateToGraph(route: String) {
    navigate(
        route
    ) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            id = graph.findStartDestination().id
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