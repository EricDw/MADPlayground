package com.example.madplayground.ui.home.source

import androidx.compose.runtime.*
import com.example.madplayground.ui.home.models.HomeScreen
import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.ui.quotes.models.MomentUiState

class HomeScreenState : HomeScreen.State {

    override val moments: MutableList<MomentUiState> = mutableStateListOf()

    override var momentOfTheDay: MomentUiState? by mutableStateOf(null)

}

@Composable
fun rememberHomeScreenState(initializer: HomeScreenState.() -> Unit = {}): HomeScreenState =
    remember {
        HomeScreenState().apply(initializer)
    }