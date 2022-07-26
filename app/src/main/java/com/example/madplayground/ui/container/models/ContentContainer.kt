package com.example.madplayground.ui.container.models

import androidx.navigation.NavHostController
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.screens.Screen
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

    sealed interface Event {

        object SettingsTabClicked : Event

        object NavigationButtonClicked : Event

        object TimelineTabClicked : Event

        object FABClicked : Event

    }

    interface State {

        val themeType: StateFlow<Settings.ThemeType>

        val iconographyType: StateFlow<Settings.IconographyType>

        val shapeType: StateFlow<Settings.ShapeType>

        val navigationLabelVisibility: StateFlow<Settings.NavigationLabelVisibility>

    }

    interface ViewModel {

        val state: State

        val commandHandler: (Command) -> Unit

        sealed interface Command {


        }

    }

    companion object {
        const val HOME_GRAPH_ROUTE: String = "home_graph"
        const val SETTINGS_GRAPH_ROUTE: String = "settings_graph"
    }

}