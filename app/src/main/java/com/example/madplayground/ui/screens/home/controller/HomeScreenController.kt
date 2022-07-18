package com.example.madplayground.ui.screens.home.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.ui.screens.home.HomeScreen
import com.example.madplayground.ui.screens.home.api.HomeScreen
import com.example.madplayground.ui.screens.home.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreenController(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreen.ViewModel = hiltViewModel<HomeScreenViewModel>(),
) {

    val state by homeScreenViewModel.state.collectAsState()

    HomeScreen(
        modifier = modifier,
        state = state
    )

}