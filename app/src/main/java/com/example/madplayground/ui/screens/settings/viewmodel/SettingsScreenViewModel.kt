package com.example.madplayground.ui.screens.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.logs.api.Logger
import com.example.madplayground.messages.api.Message
import com.example.madplayground.models.apis.App
import com.example.madplayground.models.apis.Settings
import com.example.madplayground.ui.screens.settings.SettingsScreenState
import com.example.madplayground.ui.screens.settings.api.SettingsScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val logger: Logger,
    private val app: App,
) : ViewModel(), SettingsScreen.ViewModel {

    private val tag = this::class.simpleName

    private val settingsScreenState = SettingsScreenState()

    private val _stateFlow = MutableStateFlow(settingsScreenState)

    override val stateFlow: StateFlow<SettingsScreen.State> =
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

    override val eventHandler: Message.Handler<SettingsScreen.Event> = Message.Handler { theEvent ->

        when (theEvent) {

            SettingsScreen.Event.ThemeTypeClicked -> {

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

                    logger.logDebug(tag = tag, message = "Updating ThemeType to: $newThemeType")

                }

            }

            SettingsScreen.Event.IconTypeClicked  -> {

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

            SettingsScreen.Event.ShapeTypeClicked  -> {

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

            SettingsScreen.Event.Started          -> {
                /* no-op */
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

    }
}