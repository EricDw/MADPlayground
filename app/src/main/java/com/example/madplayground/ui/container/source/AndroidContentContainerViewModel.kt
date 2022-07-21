package com.example.madplayground.ui.container.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.models.ContentContainer.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AndroidContentContainerViewModel @Inject constructor(
    logs: Logs,
    settings: Settings,
) : ViewModel(), ContentContainer.ViewModel {

    private val delegate = ContentContainerViewModel(
        logs = logs,
        settings = settings,
        scope = viewModelScope
    )

    override val stateFlow: StateFlow<State> =
        delegate.stateFlow

    override val actionHandler = delegate.actionHandler

}