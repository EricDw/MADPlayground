package com.example.madplayground.ui.app.viewmodel

import androidx.lifecycle.ViewModel
import com.example.madplayground.logs.api.Logger
import com.example.madplayground.ui.app.api.App
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val logger: Logger,
) : ViewModel(), App.ViewModel {

    private var appState = AppState()

    private val _stateFlow = MutableStateFlow(appState)

    override val stateFlow: StateFlow<App.State> =
        _stateFlow.asStateFlow()

    override fun handle(event: App.Event) {
        when (event) {
            App.Event.SettingsClick -> {
                appState.isDarkTheme = !appState.isDarkTheme
                logger.logDebug("Is dark theme changes too: ${appState.isDarkTheme}")
            }
        }
    }
}