package com.example.madplayground.features.settings.ui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.features.app.apis.App
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.settings.ui.screens.viewmodel.SettingsScreenViewModel
import com.example.madplayground.features.settings.ui.screens.api.SettingsScreen
import com.example.madplayground.features.settings.ui.screens.api.SettingsScreen.*
import com.example.madplayground.features.settings.ui.screens.api.SettingsScreen.ViewModel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AndroidSettingsScreenViewModel @Inject constructor(
    private val app: App,
) : ViewModel(), SettingsScreen.ViewModel {

    private val delegate = SettingsScreenViewModel(
        app = app,
        scope = viewModelScope
    )

    override val stateFlow: StateFlow<State> = delegate.stateFlow

    override val actionHandler: Message.Handler<Action> = delegate.actionHandler

}