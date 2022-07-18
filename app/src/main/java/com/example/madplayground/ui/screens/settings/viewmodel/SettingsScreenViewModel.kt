package com.example.madplayground.ui.screens.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.features.app.apis.App
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.settings.apis.Settings
import com.example.madplayground.ui.screens.settings.SettingsScreenState
import com.example.madplayground.ui.screens.settings.api.SettingsScreen
import com.example.madplayground.ui.screens.settings.api.SettingsScreen.*
import com.example.madplayground.ui.screens.settings.api.SettingsScreen.ViewModel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val app: App,
) : ViewModel(), SettingsScreen.ViewModel {

    private val tag = this::class.simpleName

    private val logs
        get() = app.logs

    private val settingsScreenState = SettingsScreenState()

    private val _stateFlow = MutableStateFlow(settingsScreenState)

    override val stateFlow: StateFlow<State> =
        _stateFlow.asStateFlow()

    private var themeSynchronizer: Job? = app.settings.themeType
        .onEach { themeType ->

            settingsScreenState.themeType = themeType

        }.launchIn(viewModelScope)

    private var iconSynchronizer: Job? = app.settings.iconographyType
        .onEach { iconographyType ->

            settingsScreenState.iconType = iconographyType

        }.launchIn(viewModelScope)

    private var shapeSynchronizer: Job? = app.settings.shapeType
        .onEach { shapeType ->

            settingsScreenState.shapeType = shapeType

        }.launchIn(viewModelScope)

    private var labelSynchronizer: Job? = app.settings.navigationLabelVisibility
        .onEach { visibility ->

            settingsScreenState.navigationLabelVisibility = visibility

        }.launchIn(viewModelScope)

    override val actionHandler: Message.Handler<Action> = Message.Handler { theAction ->

        when (theAction) {

            Action.CycleThemeType       -> {

                val newThemeType = when (settingsScreenState.themeType) {

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

                viewModelScope.launch {

                    app.settings.setThemeType(
                        newThemeType
                    )

                    logs.logDebug(tag = tag, message = "Updating ThemeType to: $newThemeType")

                }

            }

            Action.CycleIconType        -> {

                val newIconType = when (settingsScreenState.iconType) {

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

                viewModelScope.launch {
                    app.settings.setIconographyType(
                        newIconType
                    )
                }
            }

            Action.CycleShapeType       -> {

                val newShapeType = when (settingsScreenState.shapeType) {

                    Settings.ShapeType.ROUNDED -> {
                        Settings.ShapeType.CUT
                    }

                    Settings.ShapeType.CUT     -> {
                        Settings.ShapeType.ROUNDED
                    }

                }

                viewModelScope.launch {
                    app.settings.setShapeType(
                        newShapeType = newShapeType
                    )
                }
            }

            Action.CycleLabelVisibility -> {

                val newVisibility = when (
                    settingsScreenState.navigationLabelVisibility
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

                viewModelScope.launch {
                    app.settings.setNavigationLabelVisibility(
                        newVisibility = newVisibility
                    )
                }
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        themeSynchronizer?.cancel()
        themeSynchronizer = null

        iconSynchronizer?.cancel()
        iconSynchronizer = null

        shapeSynchronizer?.cancel()
        shapeSynchronizer = null

        labelSynchronizer?.cancel()
        labelSynchronizer = null

    }
}