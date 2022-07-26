package com.example.madplayground.ui.container.source

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
import com.example.madplayground.ui.config.CombinedWindowType.*
import com.example.madplayground.ui.config.LocalWindowConfiguration
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.home.screens.controller.TimelineScreenController
import com.example.madplayground.ui.logs.source.LocalLogs
import com.example.madplayground.ui.moments.screens.controller.MomentFormScreenController
import com.example.madplayground.ui.screens.TimelineScreen
import com.example.madplayground.ui.screens.MomentFormScreen
import com.example.madplayground.ui.screens.Screen
import com.example.madplayground.ui.screens.SettingsScreen
import com.example.madplayground.ui.settings.source.SettingsScreenController

@Composable
fun ContentContainerController(
    contentContainerViewModel: ContentContainer.ViewModel = hiltViewModel<AndroidContentContainerViewModel>(),
) {

    val tag = "ContentContainerController"

    val logs = LocalLogs.current

    val windowConfiguration = LocalWindowConfiguration.current

    val navHostController: NavHostController = rememberNavController()

    val containerInterface = remember {
        ContentContainerControllerImpl(
            contentContainerState = contentContainerViewModel.state,
            navHostController = navHostController
        )
    }

    ContentContainer(
        contentContainer = containerInterface,
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

                    TimelineScreenController(
                        contentContainer = containerInterface,
                        modifier = screenModifier
                    )

                }

                composable(route = MomentFormScreen.ROUTE) {

                    MomentFormScreenController(
                        container = containerInterface,
                        modifier = screenModifier,
                    )

                }

            }

            navigation(startDestination = SettingsScreen.ROUTE,
                       route = ContentContainer.SETTINGS_GRAPH_ROUTE) {

                composable(route = SettingsScreen.ROUTE) {

                    SettingsScreenController(
                        contentContainer = containerInterface,
                        modifier = screenModifier,
                    )

                }

            }

        }

    }

    LaunchedEffect(
        key1 = windowConfiguration,
        key2 = containerInterface.currentScreen,
    ) {

        with (containerInterface) {

            when (windowConfiguration.combinedWindowType) {

                COMPACT_WIDTH_COMPACT_HEIGHT   -> {

                    when (currentScreen) {

                        is Screen.EMPTY     -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                        is TimelineScreen -> {
                            showTopAppBar = false
                            showBottomNavBar = true
                            showNavigationRail = false
                            showBottomFAB = true
                        }

                        is SettingsScreen   -> {
                            showTopAppBar = true
                            showBottomFAB = false
                        }

                        is MomentFormScreen -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                    }

                    logs.logDebug(
                        tag = tag,
                        message = "$windowConfiguration handled!"
                    )

                }

                COMPACT_WIDTH_MEDIUM_HEIGHT    -> {

                    when (currentScreen) {

                        is Screen.EMPTY     -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                        is TimelineScreen -> {
                            showTopAppBar = true
                            showBottomNavBar = true
                            showNavigationRail = false
                            showBottomFAB = true
                        }

                        is SettingsScreen   -> {
                            showTopAppBar = true
                            showBottomNavBar = false
                            showBottomFAB = false
                        }

                        is MomentFormScreen -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                    }

                    logs.logDebug(
                        tag = tag,
                        message = "$windowConfiguration handled!"
                    )

                }

                COMPACT_WIDTH_EXPANDED_HEIGHT  -> {

                    when (currentScreen) {

                        is Screen.EMPTY     -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                        is TimelineScreen -> {
                            showTopAppBar = true
                            showBottomNavBar = true
                            showNavigationRail = false
                            showBottomFAB = true
                        }

                        is SettingsScreen   -> {
                            showTopAppBar = true
                            showBottomFAB = false
                        }

                        is MomentFormScreen -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                    }

                    logs.logDebug(
                        tag = tag,
                        message = "$windowConfiguration not handled!"
                    )

                }

                MEDIUM_WIDTH_COMPACT_HEIGHT    -> {

                    when (currentScreen) {

                        is Screen.EMPTY     -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                        is TimelineScreen -> {
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is SettingsScreen   -> {
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is MomentFormScreen -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                    }

                    logs.logDebug(
                        tag = tag,
                        message = "$windowConfiguration handled!"
                    )

                }

                MEDIUM_WIDTH_MEDIUM_HEIGHT     -> {

                    when (currentScreen) {

                        is Screen.EMPTY     -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                        is TimelineScreen -> {
                            showTopAppBar = true
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is SettingsScreen   -> {
                            showTopAppBar = true
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is MomentFormScreen -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                    }

                    logs.logDebug(
                        tag = tag,
                        message = "$windowConfiguration handled!"
                    )

                }

                MEDIUM_WIDTH_EXPANDED_HEIGHT   -> {

                    when (currentScreen) {

                        is Screen.EMPTY     -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                        is TimelineScreen -> {
                            showTopAppBar = true
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is SettingsScreen   -> {
                            showTopAppBar = true
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is MomentFormScreen -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                    }

                    logs.logDebug(
                        tag = tag,
                        message = "$windowConfiguration handled!"
                    )

                }

                EXPANDED_WIDTH_COMPACT_HEIGHT  -> {

                    when (currentScreen) {

                        is Screen.EMPTY     -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                        is TimelineScreen -> {
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is SettingsScreen   -> {
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is MomentFormScreen -> {
                            showTopAppBar = false
                            showNavigationRail = false
                            showBottomNavBar = false
                            showBottomFAB = false
                        }

                    }

                    logs.logDebug(
                        tag = tag,
                        message = "$windowConfiguration handled!"
                    )

                }

                EXPANDED_WIDTH_MEDIUM_HEIGHT   -> {

                    when (currentScreen) {

                        is Screen.EMPTY     -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                        is TimelineScreen -> {
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is SettingsScreen   -> {
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is MomentFormScreen -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                    }

                    logs.logDebug(
                        tag = tag,
                        message = "$windowConfiguration handled!"
                    )

                }

                EXPANDED_WIDTH_EXPANDED_HEIGHT -> {

                    when (currentScreen) {

                        is Screen.EMPTY     -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                        is TimelineScreen -> {
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is SettingsScreen   -> {
                            showBottomNavBar = false
                            showNavigationRail = true
                            showBottomFAB = false
                        }

                        is MomentFormScreen -> {
                            showTopAppBar = false
                            showBottomNavBar = false
                            showNavigationRail = false
                            showBottomFAB = false
                        }

                    }

                    logs.logDebug(
                        tag = tag,
                        message = "$windowConfiguration handled!"
                    )

                }

            }

        }

    }

}

private class ContentContainerControllerImpl(
    private val contentContainerState: ContentContainer.State,
    override val navHostController: NavHostController,
) : ContentContainer.Controller {

    override val state: ContentContainer.State = contentContainerState

    override var currentScreen: Screen by mutableStateOf(Screen.EMPTY)

    override var showTopAppBar: Boolean by mutableStateOf(false)

    override var showBottomNavBar: Boolean by mutableStateOf(false)

    override var showNavigationRail: Boolean by mutableStateOf(false)

    override var showBottomFAB: Boolean by mutableStateOf(false)

}