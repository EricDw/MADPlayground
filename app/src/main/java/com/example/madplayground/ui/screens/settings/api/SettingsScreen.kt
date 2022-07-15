package com.example.madplayground.ui.screens.settings.api

import com.example.madplayground.messages.api.Message
import com.example.madplayground.models.apis.Settings
import kotlinx.coroutines.flow.StateFlow

interface SettingsScreen {

    companion object {
        const val ROUTE: String = "settings"
    }

    sealed interface Event : Message {

        object Started : Event

        object ThemeTypeClicked : Event

        object IconTypeClicked : Event

        object ShapeTypeClicked : Event

    }

    interface State {

        val themeType: Settings.ThemeType

        val iconType: Settings.IconographyType

        val shapeType: Settings.ShapeType

    }

    interface ViewModel {

        val stateFlow: StateFlow<State>

        val eventHandler: Message.Handler<Event>

    }

}