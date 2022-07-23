package com.example.madplayground.ui.moments.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.moments.usecases.CreateMomentUseCase
import com.example.madplayground.ui.moments.models.MomentFormUiState
import com.example.madplayground.ui.moments.models.MomentFormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AndroidMomentFormScreenMomentFormViewModel @Inject constructor(
    logs: Logs,
    createMomentUseCase: CreateMomentUseCase,
) : ViewModel(), MomentFormViewModel {

    private val delegate = MomentFormViewModelImpl(
        logs = logs,
        createMomentUseCase = createMomentUseCase,
        scope = viewModelScope,
    )

    override val state: StateFlow<MomentFormUiState> =
        delegate.state

    override val actionHandler: Message.Handler<MomentFormViewModel.Action> =
        delegate.actionHandler

}