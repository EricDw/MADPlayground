package com.example.madplayground.ui.app.api

import kotlinx.coroutines.flow.StateFlow

interface App {

    sealed interface Event {
        interface Handler {
            fun handle(event: Event)
        }

        object SettingsClick : Event

    }

    interface State {

        val title: String

        val isDarkTheme: Boolean

    }

    interface ViewModel: Event.Handler {

        val stateFlow: StateFlow<State>

    }

}