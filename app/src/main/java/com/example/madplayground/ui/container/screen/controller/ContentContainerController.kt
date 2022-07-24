package com.example.madplayground.ui.container.screen.controller

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
import com.example.madplayground.ui.config.CombinedWindowType.*
import com.example.madplayground.ui.config.LocalWindowConfiguration
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.screen.ContentContainer
import com.example.madplayground.ui.container.source.AndroidContentContainerViewModel
import com.example.madplayground.ui.home.screens.controller.HomeScreenController
import com.example.madplayground.ui.logs.LocalLogs
import com.example.madplayground.ui.moments.screens.controller.MomentFormScreenController
import com.example.madplayground.ui.screen.HomeScreen
import com.example.madplayground.ui.screen.MomentFormScreen
import com.example.madplayground.ui.screen.Screen
import com.example.madplayground.ui.screen.SettingsScreen
import com.example.madplayground.ui.settings.screen.controller.SettingsScreenController

@Composable
fun ContentContainerController(
    contentContainerViewModel: ContentContainer.ViewModel = hiltViewModel<AndroidContentContainerViewModel>(),
) {

    val tag = "ContentContainerController"

    val logs = LocalLogs.current

    val windowConfiguration = LocalWindowConfiguration.current

    val contentContainerState by contentContainerViewModel.stateFlow.collectAsState()

    var showTopAppBar: Boolean by remember {
        mutableStateOf(false)
    }

    var showBottomNavBar: Boolean by remember {
        mutableStateOf(false)
    }

    var showNavigationRail: Boolean by remember {
        mutableStateOf(false)
    }

    var showBottomFAB: Boolean by remember {
        mutableStateOf(false)
    }

    val navHostController: NavHostController = rememberNavController()

    var currentScreen: Screen by remember {
        mutableStateOf(Screen.EMPTY)
    }

    val containerInterface = remember {
        object : ContentContainer.Controller {

            override val state: ContentContainer.State
                get() = contentContainerState

            override val navHostController = navHostController

            override val currentScreen: Screen
                get() = currentScreen

            override fun setScreen(newScreen: Screen) {
                currentScreen = newScreen
            }

        }
    }

    ContentContainer(
        contentContainer = containerInterface,
        modifier = Modifier.fillMaxSize(),
        state = contentContainerState,
        showTopAppBar = showTopAppBar,
        showBottomNavBar = showBottomNavBar,
        showNavigationRail = showNavigationRail,
        showBottomFAB = showBottomFAB,
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

                composable(route = HomeScreen.ROUTE) {

                    HomeScreenController(
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
        key2 = currentScreen,
    ) {

        when (windowConfiguration.combinedWindowType) {

            COMPACT_WIDTH_COMPACT_HEIGHT   -> {

                when (currentScreen) {

                    is Screen.EMPTY     -> {
                        showTopAppBar = false
                        showBottomNavBar = false
                        showNavigationRail = false
                        showBottomFAB = false
                    }

                    is HomeScreen       -> {
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

                    }

                    is HomeScreen       -> {
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
                    message = "$windowConfiguration handled!"
                )

            }

            COMPACT_WIDTH_EXPANDED_HEIGHT  -> {

                when (currentScreen) {

                    is Screen.EMPTY     -> {

                    }

                    is HomeScreen       -> {
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

                    }

                    is HomeScreen       -> {
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

                    }

                    is HomeScreen       -> {
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

                    is HomeScreen       -> {
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

                    is HomeScreen       -> {
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

                    is HomeScreen       -> {
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

                    is HomeScreen       -> {
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