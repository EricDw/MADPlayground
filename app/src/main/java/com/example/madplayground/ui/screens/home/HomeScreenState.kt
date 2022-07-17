package com.example.madplayground.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.madplayground.ui.screens.home.api.HomeScreen
import com.example.madplayground.features.quotes.apis.Quote

class HomeScreenState : HomeScreen.State {

    override val quotes: MutableList<Quote.State> = mutableStateListOf()

}

@Composable
fun rememberHomeScreenState(initializer: HomeScreenState.() -> Unit = {}): HomeScreenState =
    remember {
        HomeScreenState().apply(initializer)
    }