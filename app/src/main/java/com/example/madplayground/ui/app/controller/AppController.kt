package com.example.madplayground.ui.app.controller

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.madplayground.ui.app.App
import com.example.madplayground.ui.app.api.App
import com.example.madplayground.ui.app.viewmodel.AppViewModel
import com.example.madplayground.ui.screens.home.api.HomeScreen
import com.example.madplayground.ui.screens.home.controller.HomeScreenController
import com.example.madplayground.ui.screens.home.viewmodel.HomeScreenViewModel
import com.example.madplayground.ui.theme.MADPlaygroundTheme

@Composable
fun AppController(
    appViewModel: App.ViewModel,
) {

    val appState by appViewModel.stateFlow.collectAsState()

    val navController: NavHostController = rememberNavController()

    App(
        state = appState,
        eventHandler = appViewModel,
        modifier = Modifier.fillMaxSize(),
    ) {

        MADPlaygroundTheme() {

            NavHost(
                navController = navController,
                startDestination = HomeScreen.ROUTE
            ) {

                composable(HomeScreen.ROUTE) {

                    HomeScreenController(
                        homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
                    )

                }

            }

        }

    }

}