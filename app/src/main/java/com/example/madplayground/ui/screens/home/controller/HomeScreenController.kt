package com.example.madplayground.ui.screens.home.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.madplayground.ui.screens.home.HomeScreen
import com.example.madplayground.ui.screens.home.api.HomeScreen

@Composable
fun HomeScreenController(
    homeScreenViewModel: HomeScreen.ViewModel,
) {

    val state by homeScreenViewModel.state.collectAsState()

    HomeScreen(
        state = state
    )

}