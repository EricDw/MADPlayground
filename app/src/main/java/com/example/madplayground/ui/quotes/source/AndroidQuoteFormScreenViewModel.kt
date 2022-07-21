package com.example.madplayground.ui.moments.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.app.models.App
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.ui.moments.models.MomentFormScreen
import com.example.madplayground.ui.moments.models.MomentFormScreen.*
import com.example.madplayground.ui.moments.models.MomentFormScreen.ViewModel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AndroidMomentFormScreenViewModel @Inject constructor(
    app: App,
) : ViewModel(), MomentFormScreen.ViewModel {

    private val delegate = MomentFormScreenViewModel(
        app = app,
        scope = viewModelScope,
    )

    override val state: StateFlow<State> =
        delegate.state

    override val actionHandler: Message.Handler<Action> =
        delegate.actionHandler

}