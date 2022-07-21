package com.example.madplayground.ui.settings.source

import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.settings.models.SettingsScreen
import com.example.madplayground.ui.settings.models.SettingsScreen.State
import com.example.madplayground.ui.settings.models.SettingsScreen.ViewModel.Action
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsScreenViewModel @Inject constructor(
    private val logs: Logs,
    private val settings: Settings,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate),
) : SettingsScreen.ViewModel, Logs by logs, Settings by settings {

    private val tag = this::class.simpleName

    private val settingsScreenState = SettingsScreenState()

    private val _stateFlow = MutableStateFlow(settingsScreenState)

    override val stateFlow: StateFlow<State> =
        _stateFlow.asStateFlow()

    init {

        logDebug(
            tag = tag,
            message = "Initializing"
        )

        themeType.onEach { themeType ->

            logDebug(
                tag = tag,
                message = "ThemeType Changed too: $themeType"
            )

            settingsScreenState.themeType.update { themeType }

        }.launchIn(scope)

        iconographyType.onEach { iconographyType ->

            logDebug(
                tag = tag,
                message = "IconographyType Changed too: $iconographyType"
            )

            settingsScreenState.iconType.update { iconographyType }

        }.launchIn(scope)

        shapeType.onEach { shapeType ->

            logDebug(
                tag = tag,
                message = "ShapeType Changed too: $shapeType"
            )

            settingsScreenState.shapeType.update { shapeType }

        }.launchIn(scope)

        navigationLabelVisibility.onEach { visibility ->

            logDebug(
                tag = tag,
                message = "NavigationLabelVisibility Changed too: $visibility"
            )

            settingsScreenState.navigationLabelVisibility.update { visibility }

        }.launchIn(scope)

        logDebug(
            tag = tag,
            message = "Initialized"
        )

    }

    override val actionHandler: Message.Handler<Action> = Message.Handler { theAction ->

        logDebug(
            tag = tag,
            message = "Received: $theAction"
        )

        when (theAction) {

            Action.CycleThemeType       -> {

                val newThemeType = when (
                    settingsScreenState.themeType.value
                ) {

                    Settings.ThemeType.LIGHT  -> {
                        Settings.ThemeType.DARK
                    }

                    Settings.ThemeType.DARK   -> {
                        Settings.ThemeType.SYSTEM
                    }

                    Settings.ThemeType.SYSTEM -> {
                        Settings.ThemeType.LIGHT
                    }

                }

                scope.launch {

                    setThemeType(
                        newThemeType
                    )

                }

            }

            Action.CycleIconType        -> {

                val newIconType = when (
                    settingsScreenState.iconType.value
                ) {

                    Settings.IconographyType.DEFAULT  -> {
                        Settings.IconographyType.SHARP
                    }

                    Settings.IconographyType.SHARP    -> {
                        Settings.IconographyType.OUTLINED
                    }

                    Settings.IconographyType.OUTLINED -> {
                        Settings.IconographyType.ROUNDED
                    }

                    Settings.IconographyType.ROUNDED  -> {
                        Settings.IconographyType.TWO_TONE
                    }

                    Settings.IconographyType.TWO_TONE -> {
                        Settings.IconographyType.DEFAULT
                    }
                }

                scope.launch {
                    setIconographyType(
                        newIconType
                    )
                }
            }

            Action.CycleShapeType       -> {

                val newShapeType = when (
                    settingsScreenState.shapeType.value
                ) {

                    Settings.ShapeType.ROUNDED -> {
                        Settings.ShapeType.CUT
                    }

                    Settings.ShapeType.CUT     -> {
                        Settings.ShapeType.ROUNDED
                    }

                }

                scope.launch {
                    setShapeType(
                        newShapeType = newShapeType
                    )
                }
            }

            Action.CycleLabelVisibility -> {

                val newVisibility = when (
                    settingsScreenState.navigationLabelVisibility.value
                ) {

                    Settings.NavigationLabelVisibility.NEVER         -> {
                        Settings.NavigationLabelVisibility.ALWAYS
                    }

                    Settings.NavigationLabelVisibility.ALWAYS        -> {
                        Settings.NavigationLabelVisibility.WHEN_SELECTED
                    }

                    Settings.NavigationLabelVisibility.WHEN_SELECTED -> {
                        Settings.NavigationLabelVisibility.NEVER
                    }

                }

                scope.launch {
                    setNavigationLabelVisibility(
                        newVisibility = newVisibility
                    )
                }
            }

        }

    }

}