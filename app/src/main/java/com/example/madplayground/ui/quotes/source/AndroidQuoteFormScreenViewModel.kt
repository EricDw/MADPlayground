package com.example.madplayground.ui.quotes.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.moments.usecases.CreateMomentUseCase
import com.example.madplayground.ui.moments.models.MomentFormScreen
import com.example.madplayground.ui.moments.models.MomentFormScreen.State
import com.example.madplayground.ui.moments.models.MomentFormScreen.ViewModel.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AndroidMomentFormScreenViewModel @Inject constructor(
    logs: Logs,
    createMomentUseCase: CreateMomentUseCase,
) : ViewModel(), MomentFormScreen.ViewModel {

    private val delegate = MomentFormScreenViewModel(
        logs = logs,
        createMomentUseCase = createMomentUseCase,
        scope = viewModelScope,
    )

    override val state: StateFlow<State> =
        delegate.state

    override val actionHandler: Message.Handler<Action> =
        delegate.actionHandler

}