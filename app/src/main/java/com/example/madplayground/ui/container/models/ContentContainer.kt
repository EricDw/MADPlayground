package com.example.madplayground.ui.container.models

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.madplayground.domain.settings.models.Settings
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
interface ContentContainer {

    val state: State

    val showTopAppBar: Boolean

    val titleId: Int?

    val navigationIcon: (@Composable () -> Unit)?

    val topAppBarActions: @Composable RowScope.() -> Unit

    val drawerGesturesEnabled: Boolean

    val drawerContent: @Composable ColumnScope.() -> Unit

    val showBottomNavBar: Boolean

    val isTimelineSelected: Boolean

    val isSettingsSelected: Boolean

    val onTimelineClick: () -> Unit

    val onSettingClick: () -> Unit

    val showNavigationRail: Boolean

    val railHeader: @Composable (ColumnScope.() -> Unit)?

    val showFab: Boolean

    val floatingActionButton: @Composable () -> Unit

    val fabPosition: FabPosition

    val snackbarHost: @Composable () -> Unit

    interface Controller : ContentContainer {

        val navHostController: NavHostController

        val drawerState: DrawerState

        override var showTopAppBar: Boolean

        override var navigationIcon: (@Composable () -> Unit)?

        override var topAppBarActions: @Composable RowScope.() -> Unit

        override var drawerGesturesEnabled: Boolean

        override var drawerContent: @Composable ColumnScope.() -> Unit

        override var titleId: Int?

        override var showBottomNavBar: Boolean

        override var isTimelineSelected: Boolean

        override var isSettingsSelected: Boolean

        override var onTimelineClick: () -> Unit

        override var onSettingClick: () -> Unit

        override var showNavigationRail: Boolean

        override var railHeader: @Composable (ColumnScope.() -> Unit)?

        override var showFab: Boolean

        override var fabPosition: FabPosition

        override var floatingActionButton: @Composable () -> Unit

        override var snackbarHost: @Composable () -> Unit

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
        const val TIMELINE_GRAPH_ROUTE: String = "timeline_graph"
        const val SETTINGS_GRAPH_ROUTE: String = "settings_graph"
    }

}