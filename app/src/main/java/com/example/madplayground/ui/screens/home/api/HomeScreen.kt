package com.example.madplayground.ui.screens.home.api

import kotlinx.coroutines.flow.StateFlow

interface HomeScreen {

    companion object {

        const val ROUTE: String = "home"

    }

    sealed interface Event {

        interface Handler {
            fun handle(event: Event)
        }

    }

    interface State {

        val title: String

    }

    interface ViewModel : Event.Handler {

        val state: StateFlow<State>

    }

}