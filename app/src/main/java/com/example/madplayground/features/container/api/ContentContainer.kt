package com.example.madplayground.features.container.api

import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.settings.apis.Settings
import kotlinx.coroutines.flow.StateFlow

interface ContentContainer {

    companion object {
        const val HOME_GRAPH_ROUTE: String = "home_graph"
        const val SETTINGS_GRAPH_ROUTE: String = "settings_graph"
    }

    enum class ScreenContext {
        HOME, SETTINGS, QUOTE_FORM;
    }

    sealed interface Event : Message {

        object SettingsTabClicked : Event

        object NavigationButtonClicked : Event

        object HomeTabClicked : Event

        object FABClicked : Event

    }

    interface State {

        val screenContext: ScreenContext

        val themeType: Settings.ThemeType

        val iconographyType: Settings.IconographyType

        val shapeType: Settings.ShapeType

        val navigationLabelVisibility: Settings.NavigationLabelVisibility

    }

    interface ViewModel {

        val stateFlow: StateFlow<State>

        val actionHandler: Message.Handler<Action>

        sealed interface Action : Message {

            data class SwitchContexts(
                val newContext: ScreenContext,
            ) : Action

        }

    }

}