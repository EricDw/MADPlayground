package com.example.madplayground.ui.container.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.features.app.apis.App
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.quotes.controllers.QuoteController
import com.example.madplayground.ui.container.ContentContainerState
import com.example.madplayground.ui.container.api.ContentContainer
import com.example.madplayground.ui.container.api.ContentContainer.State
import com.example.madplayground.ui.container.api.ContentContainer.ViewModel.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentContainerViewModel @Inject constructor(
    private val app: App,
) : ViewModel(), ContentContainer.ViewModel, App by app {

    private val tag = this::class.simpleName

    private var state = ContentContainerState()

    private val _stateFlow: MutableStateFlow<State> =
        MutableStateFlow(state)

    override val stateFlow: StateFlow<State> =
        _stateFlow.asStateFlow()

    private var themeSynchronizer: Job? = app.settings.themeType
        .onEach { themeType ->

            state.themeType = themeType

        }.launchIn(viewModelScope)

    private var iconSynchronizer: Job? = app.settings.iconographyType
        .onEach { iconographyType ->

            state.iconographyType = iconographyType

        }.launchIn(viewModelScope)

    private var shapeSynchronizer: Job? = app.settings.shapeType
        .onEach { shapeType ->

            state.shapeType = shapeType

        }.launchIn(viewModelScope)

    private var labelSynchronizer: Job? = app.settings.navigationLabelVisibility
        .onEach { visibility ->

            state.navigationLabelVisibility = visibility

        }.launchIn(viewModelScope)

    override val actionHandler = Message.Handler<Action> { action ->
        when (action) {

            is Action.SwitchContexts -> {
                state.screenContext = action.newContext
            }

            is Action.AddNewQuote    -> {
                viewModelScope.launch {
                    quotes.addNewQuote(
                        QuoteController(content = action.content)
                    )
                }
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        themeSynchronizer?.cancel()
        themeSynchronizer = null

        iconSynchronizer?.cancel()
        iconSynchronizer = null

        shapeSynchronizer?.cancel()
        shapeSynchronizer = null

        labelSynchronizer?.cancel()
        labelSynchronizer = null

    }

}