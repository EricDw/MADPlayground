package com.example.madplayground.ui.container.api

import com.example.madplayground.messages.api.Message
import com.example.madplayground.models.apis.Settings
import com.example.madplayground.ui.config.WindowConfiguration
import kotlinx.coroutines.flow.StateFlow

interface ContentContainer {

    companion object {
        const val HOME_GRAPH_ROUTE: String = "home_graph"
        const val SETTINGS_GRAPH_ROUTE: String = "settings_graph"
    }

    enum class ScreenContext {
        HOME, SETTINGS;
    }

    sealed interface Event : Message {

        object SettingsTabClicked : Event

        object NavigationButtonClicked : Event

        object HomeScreenStarted : Event

        object SettingsScreenStarted : Event

        object HomeTabClicked : Event

        object DrawerClosed : Event

        class WindowConfigurationChanged(
            val newWindowConfiguration: WindowConfiguration,
        ) : Event

    }

    sealed interface SideEffect {

        val completionHandler: () -> Unit

        class NavigateToHomeTab(
            override val completionHandler: () -> Unit = {},
        ) : SideEffect

        class NavigateToSettingsTab(
            override val completionHandler: () -> Unit = {},
        ) : SideEffect

        class NavigateBack(
            override val completionHandler: () -> Unit = {},
        ) : SideEffect

    }

    interface State {

        val screenContext: ScreenContext

        val themeType: Settings.ThemeType

        val iconographyType: Settings.IconographyType

        val shapeType: Settings.ShapeType

        val triggerDrawerToOpen: Boolean

        val showNavigationIcon: Boolean

        val showTopAppBar: Boolean

        val showBottomNavBar: Boolean

        val showNavigationRail: Boolean

    }

    interface ViewModel {

        val stateFlow: StateFlow<State>

        val sideEffect: StateFlow<SideEffect?>

        val eventHandler: Message.Handler<Event>

    }

}