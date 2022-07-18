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
class AndroidSettingsScreenViewModel @Inject constructor(
    private val app: App,
) : ViewModel(), SettingsScreen.ViewModel {

    private val tag = this::class.simpleName

    private val logs
        get() = app.logs

    private val delegate = SettingsScreenViewModel(
        app = app,
        scope = viewModelScope
    )

    override val stateFlow: StateFlow<State> = delegate.stateFlow

    override val actionHandler: Message.Handler<Action> = delegate.actionHandler

}