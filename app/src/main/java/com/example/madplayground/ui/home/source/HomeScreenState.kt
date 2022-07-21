package com.example.madplayground.ui.home.source

import androidx.compose.runtime.*
import com.example.madplayground.ui.home.models.HomeScreen
import com.example.madplayground.domain.moments.models.Moment

class HomeScreenState : HomeScreen.State {

    override val moments: MutableList<Moment.State> = mutableStateListOf()

    override var momentOfTheDay: Moment.State? by mutableStateOf(null)

}

@Composable
fun rememberHomeScreenState(initializer: HomeScreenState.() -> Unit = {}): HomeScreenState =
    remember {
        HomeScreenState().apply(initializer)
    }