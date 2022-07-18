package com.example.madplayground.ui.screens.settings.viewmodel

import com.example.madplayground.features.app.apis.App
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.settings.apis.Settings
import com.example.madplayground.ui.screens.settings.SettingsScreenState
import com.example.madplayground.ui.screens.settings.api.SettingsScreen
import com.example.madplayground.ui.screens.settings.api.SettingsScreen.*
import com.example.madplayground.ui.screens.settings.api.SettingsScreen.ViewModel.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsScreenViewModel @Inject constructor(
    private val app: App,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate)
) : SettingsScreen.ViewModel {

    private val tag = this::class.simpleName

    private val logs
        get() = app.logs

    private val settingsScreenState = SettingsScreenState()

    private val _stateFlow = MutableStateFlow(settingsScreenState)

    override val stateFlow: StateFlow<State> =
        _stateFlow.asStateFlow()

    init {

        app.settings.themeType
            .onEach { themeType ->

                settingsScreenState.themeType.update { themeType }

            }.launchIn(scope)

        app.settings.iconographyType
            .onEach { iconographyType ->

                settingsScreenState.iconType.update { iconographyType }

            }.launchIn(scope)

        app.settings.shapeType
            .onEach { shapeType ->

                settingsScreenState.shapeType.update { shapeType }

            }.launchIn(scope)

        app.settings.navigationLabelVisibility
            .onEach { visibility ->

                settingsScreenState.navigationLabelVisibility.update { visibility }

            }.launchIn(scope)
    }

    override val actionHandler: Message.Handler<Action> = Message.Handler { theAction ->

        when (theAction) {

            Action.CycleThemeType -> {

                val newThemeType = when (
                    settingsScreenState.themeType.value
                ) {

                    Settings.ThemeType.LIGHT -> {
                        Settings.ThemeType.DARK
                    }

                    Settings.ThemeType.DARK -> {
                        Settings.ThemeType.SYSTEM
                    }

                    Settings.ThemeType.SYSTEM -> {
                        Settings.ThemeType.LIGHT
                    }

                }

                scope.launch {

                    app.settings.setThemeType(
                        newThemeType
                    )

                    logs.logDebug(tag = tag, message = "Updating ThemeType to: $newThemeType")

                }

            }

            Action.CycleIconType -> {

                val newIconType = when (
                    settingsScreenState.iconType.value
                ) {

                    Settings.IconographyType.DEFAULT -> {
                        Settings.IconographyType.SHARP
                    }

                    Settings.IconographyType.SHARP -> {
                        Settings.IconographyType.OUTLINED
                    }

                    Settings.IconographyType.OUTLINED -> {
                        Settings.IconographyType.ROUNDED
                    }

                    Settings.IconographyType.ROUNDED -> {
                        Settings.IconographyType.TWO_TONE
                    }

                    Settings.IconographyType.TWO_TONE -> {
                        Settings.IconographyType.DEFAULT
                    }
                }

                scope.launch {
                    app.settings.setIconographyType(
                        newIconType
                    )
                }
            }

            Action.CycleShapeType -> {

                val newShapeType = when (
                    settingsScreenState.shapeType.value
                ) {

                    Settings.ShapeType.ROUNDED -> {
                        Settings.ShapeType.CUT
                    }

                    Settings.ShapeType.CUT -> {
                        Settings.ShapeType.ROUNDED
                    }

                }

                scope.launch {
                    app.settings.setShapeType(
                        newShapeType = newShapeType
                    )
                }
            }

            Action.CycleLabelVisibility -> {

                val newVisibility = when (
                    settingsScreenState.navigationLabelVisibility.value
                ) {

                    Settings.NavigationLabelVisibility.NEVER -> {
                        Settings.NavigationLabelVisibility.ALWAYS
                    }

                    Settings.NavigationLabelVisibility.ALWAYS -> {
                        Settings.NavigationLabelVisibility.WHEN_SELECTED
                    }

                    Settings.NavigationLabelVisibility.WHEN_SELECTED -> {
                        Settings.NavigationLabelVisibility.NEVER
                    }

                }

                scope.launch {
                    app.settings.setNavigationLabelVisibility(
                        newVisibility = newVisibility
                    )
                }
            }

        }

    }

}