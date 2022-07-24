package com.example.madplayground.ui.home.source

import androidx.compose.runtime.*
import com.example.madplayground.ui.screen.HomeScreen
import com.example.madplayground.ui.moments.models.MomentUiState

class HomeScreenState : HomeScreen.State {

    override val moments: MutableList<MomentUiState> = mutableStateListOf()

    override var momentOfTheDay: MomentUiState? by mutableStateOf(null)

}

@Composable
fun rememberHomeScreenState(initializer: HomeScreenState.() -> Unit = {}): HomeScreenState =
    remember {
        HomeScreenState().apply(initializer)
    }