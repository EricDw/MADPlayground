package com.example.madplayground.ui.home.source

import androidx.compose.runtime.*
import com.example.madplayground.ui.home.models.HomeScreen
import com.example.madplayground.domain.quotes.models.Quote

class HomeScreenState : HomeScreen.State {

    override val quotes: MutableList<Quote.State> = mutableStateListOf()

    override var quoteOfTheDay: Quote.State? by mutableStateOf(null)

}

@Composable
fun rememberHomeScreenState(initializer: HomeScreenState.() -> Unit = {}): HomeScreenState =
    remember {
        HomeScreenState().apply(initializer)
    }