package com.example.madplayground.features.container.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.features.app.apis.App
import com.example.madplayground.features.container.api.ContentContainer
import com.example.madplayground.features.container.api.ContentContainer.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AndroidContentContainerViewModel @Inject constructor(
    app: App,
) : ViewModel(), ContentContainer.ViewModel {

    private val delegate = ContentContainerViewModel(
        app = app,
        scope = viewModelScope
    )

    override val stateFlow: StateFlow<State> =
        delegate.stateFlow

    override val actionHandler = delegate.actionHandler

}