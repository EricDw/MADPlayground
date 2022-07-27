package com.example.madplayground.ui.container.models

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.madplayground.domain.settings.models.Settings
import kotlinx.coroutines.flow.StateFlow

interface ContentContainer {

    val state: State

    val showTopAppBar: Boolean

    val titleId: Int?

    val topAppBarIcon: (@Composable () -> Unit)?

    val topAppBarActions: @Composable RowScope.() -> Unit

    val showBottomNavBar: Boolean

    val isTimelineSelected: Boolean

    val isSettingsSelected: Boolean

    val onTimelineClick: () -> Unit

    val onSettingClick: () -> Unit

    val showNavigationRail: Boolean

    val railHeader: (@Composable ColumnScope.() -> Unit)?

    val showBottomFAB: Boolean

    val bottomFAB: @Composable () -> Unit

    interface Controller : ContentContainer {

        val navHostController: NavHostController

        override var showTopAppBar: Boolean

        override var topAppBarIcon: (@Composable () -> Unit)?

        override var topAppBarActions: @Composable RowScope.() -> Unit

        override var titleId: Int?

        override var showBottomNavBar: Boolean

        override var isTimelineSelected: Boolean

        override var isSettingsSelected: Boolean

        override var onTimelineClick: () -> Unit

        override var onSettingClick: () -> Unit

        override var showNavigationRail: Boolean

        override var railHeader: (@Composable ColumnScope.() -> Unit)?

        override var showBottomFAB: Boolean

        override var bottomFAB: @Composable () -> Unit

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