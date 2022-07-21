package com.example.madplayground.ui.home.screens.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.ui.home.screens.HomeScreen
import com.example.madplayground.ui.home.models.HomeScreen
import com.example.madplayground.ui.home.source.HomeScreenViewModel

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