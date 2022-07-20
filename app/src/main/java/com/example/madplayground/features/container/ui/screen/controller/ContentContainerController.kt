package com.example.madplayground.features.container.ui.screen.controller

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
import com.example.madplayground.features.container.ui.screen.ContentContainer
import com.example.madplayground.features.container.api.ContentContainer
import com.example.madplayground.features.container.ui.viewmodel.AndroidContentContainerViewModel
import com.example.madplayground.ui.logs.LocalLogs
import com.example.madplayground.features.home.api.HomeScreen
import com.example.madplayground.features.home.controller.HomeScreenController
import com.example.madplayground.features.quotes.ui.screens.form.api.QuoteFormScreen
import com.example.madplayground.features.quotes.ui.screens.form.controller.QuoteFormScreenController
import com.example.madplayground.features.settings.ui.screens.api.SettingsScreen
import com.example.madplayground.features.settings.ui.screens.controller.SettingsScreenController

@Composable
fun ContentContainerController(
    contentContainerViewModel: ContentContainer.ViewModel = hiltViewModel<AndroidContentContainerViewModel>(),
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

    var showBottomFAB: Boolean by remember {
        mutableStateOf(true)
    }

    val navHostController: NavHostController = rememberNavController()

    val eventHandler = Message.Handler<ContentContainer.Event> { theEvent ->

        when (theEvent) {

            ContentContainer.Event.FABClicked              -> {
                navHostController.navigate(
                    route = QuoteFormScreen.ROUTE
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

                    ContentContainer.ScreenContext.QUOTE_FORM -> {
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
        showBottomFAB = showBottomFAB,
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

                composable(
                    route = QuoteFormScreen.ROUTE
                ) {

                    QuoteFormScreenController(
                        navController = navHostController,
                        modifier = screenModifier
                    )

                    LaunchedEffect(key1 = true) {
                        contentContainerViewModel.actionHandler(
                            ContentContainer.ViewModel.Action.SwitchContexts(
                                ContentContainer.ScreenContext.QUOTE_FORM
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

    LaunchedEffect(
        key1 = windowConfiguration,
        key2 = contentContainerState.screenContext
    ) {

        when (
            windowConfiguration.combinedWindowType
        ) {

            CombinedWindowType.COMPACT_WIDTH_COMPACT_HEIGHT   -> {

                when (contentContainerState.screenContext) {

                    ContentContainer.ScreenContext.QUOTE_FORM -> {
                        showNavigationRail = false
                        showBottomNavBar = false
                        showBottomFAB = false
                        showTopAppBar = true
                    }

                    ContentContainer.ScreenContext.SETTINGS -> {
                        showBottomFAB = false
                    }

                    else -> {
                        showTopAppBar = false
                        showBottomNavBar = true
                        showNavigationRail = false
                        showBottomFAB = true
                    }

                }

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration handled!"
                )

            }

            CombinedWindowType.COMPACT_WIDTH_MEDIUM_HEIGHT    -> {

                when (contentContainerState.screenContext) {

                    ContentContainer.ScreenContext.QUOTE_FORM -> {
                        showNavigationRail = false
                        showBottomNavBar = false
                        showBottomFAB = false
                        showTopAppBar = true
                    }

                    ContentContainer.ScreenContext.SETTINGS -> {
                        showBottomFAB = false
                    }

                    else -> {
                        showBottomNavBar = true
                        showNavigationRail = false
                        showBottomFAB = true
                    }

                }

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration handled!"
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

                when (contentContainerState.screenContext) {

                    ContentContainer.ScreenContext.QUOTE_FORM -> {
                        showNavigationRail = false
                        showBottomNavBar = false
                        showBottomFAB = false
                        showTopAppBar = true
                    }

                    else -> {
                        showBottomNavBar = false
                        showNavigationRail = true
                        showBottomFAB = false
                    }

                }

                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration handled!"
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

                when (contentContainerState.screenContext) {

                    ContentContainer.ScreenContext.QUOTE_FORM -> {
                        showNavigationRail = false
                        showBottomNavBar = false
                        showBottomFAB = false
                        showTopAppBar = true
                    }

                    else -> {
                        showBottomNavBar = false
                        showNavigationRail = true
                        showBottomFAB = false
                    }

                }


                logs.logDebug(
                    tag = tag,
                    message = "$windowConfiguration handled!"
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