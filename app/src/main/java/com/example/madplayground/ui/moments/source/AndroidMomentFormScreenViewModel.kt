package com.example.madplayground.ui.moments.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.moments.usecases.CreateMomentUseCase
import com.example.madplayground.ui.moments.models.MomentFormScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidMomentFormScreenViewModel @Inject constructor(
    logs: com.example.common.logs.models.Logs,
    createMomentUseCase: CreateMomentUseCase,
) : ViewModel(), MomentFormScreen.ViewModel {

    private val delegate = MomentFormScreenViewModelImpl(
        logs = logs,
        createMomentUseCase = createMomentUseCase,
        scope = viewModelScope,
    )

    override val state = delegate.state

    override val commandHandler: (MomentFormScreen.ViewModel.Command) -> Unit =
        delegate.commandHandler

}