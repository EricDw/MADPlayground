package com.example.madplayground.ui.home.source

import androidx.compose.runtime.*
import com.example.madplayground.ui.moments.models.MomentUiState
import com.example.madplayground.ui.screens.TimelineScreen

class HomeScreenState : TimelineScreen.State {

    override val moments: MutableList<MomentUiState> = mutableStateListOf()

    override var selectedMoment: MomentUiState? by mutableStateOf(null)

}

@Composable
fun rememberHomeScreenState(
    initializer: HomeScreenState.() -> Unit = {},
): HomeScreenState = remember {
    HomeScreenState().apply(initializer)
}