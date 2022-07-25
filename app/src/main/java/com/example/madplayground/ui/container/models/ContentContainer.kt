package com.example.madplayground.ui.container.models

import androidx.navigation.NavHostController
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.screen.Screen
import kotlinx.coroutines.flow.StateFlow

interface ContentContainer {

    val state: State

    val showTopAppBar: Boolean

    val showBottomNavBar: Boolean

    val showNavigationRail: Boolean

    val showBottomFAB: Boolean

    val currentScreen: Screen

    interface Controller : ContentContainer {

        val navHostController: NavHostController

        override var currentScreen: Screen

        override var showTopAppBar: Boolean

        override var showBottomNavBar: Boolean

        override var showNavigationRail: Boolean

        override var showBottomFAB: Boolean

    }

    sealed interface Event : Message {

        object SettingsTabClicked : Event

        object NavigationButtonClicked : Event

        object HomeTabClicked : Event

        object FABClicked : Event

    }

    interface State {

        val themeType: Settings.ThemeType

        val iconographyType: Settings.IconographyType

        val shapeType: Settings.ShapeType

        val navigationLabelVisibility: Settings.NavigationLabelVisibility

    }

    interface ViewModel {

        val stateFlow: StateFlow<State>

        val actionHandler: Message.Handler<Action>

        sealed interface Action : Message {


        }

    }

    companion object {
        const val HOME_GRAPH_ROUTE: String = "home_graph"
        const val SETTINGS_GRAPH_ROUTE: String = "settings_graph"
    }

}