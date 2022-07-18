package com.example.madplayground.ui.screens.settings.api

import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.settings.apis.Settings
import kotlinx.coroutines.flow.StateFlow

interface SettingsScreen {

    companion object {
        const val ROUTE: String = "settings"
    }

    sealed interface Event : Message {

        object ThemeTypeClicked : Event

        object IconTypeClicked : Event

        object ShapeTypeClicked : Event

        object LabelVisibilityClicked : Event

    }

    interface State {

        val themeType: StateFlow<Settings.ThemeType>

        val iconType: StateFlow<Settings.IconographyType>

        val shapeType: StateFlow<Settings.ShapeType>

        val navigationLabelVisibility: StateFlow<Settings.NavigationLabelVisibility>

    }

    interface ViewModel {

        val stateFlow: StateFlow<State>

        val actionHandler: Message.Handler<Action>

        sealed interface Action : Message {

            object CycleThemeType : Action

            object CycleIconType : Action

            object CycleShapeType : Action

            object CycleLabelVisibility : Action

        }

    }

}