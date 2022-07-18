package com.example.madplayground.ui.screens.home.api

import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.quotes.apis.Quote
import kotlinx.coroutines.flow.StateFlow

interface HomeScreen {

    companion object {

        const val ROUTE: String = "home_screen"

    }

    sealed interface Event {

    }

    interface State {

        val quotes: List<Quote.State>

        val quoteOfTheDay: Quote.State?

    }

    interface ViewModel {

        val state: StateFlow<State>

        sealed interface Action : Message {

        }

    }

}