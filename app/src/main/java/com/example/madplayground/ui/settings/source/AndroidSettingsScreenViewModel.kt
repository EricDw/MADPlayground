package com.example.madplayground.ui.settings.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.app.models.App
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.ui.settings.models.SettingsScreen
import com.example.madplayground.ui.settings.models.SettingsScreen.*
import com.example.madplayground.ui.settings.models.SettingsScreen.ViewModel.*
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