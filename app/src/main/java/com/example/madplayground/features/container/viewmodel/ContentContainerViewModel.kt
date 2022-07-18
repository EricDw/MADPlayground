package com.example.madplayground.features.container.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.features.app.apis.App
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.quotes.controllers.QuoteController
import com.example.madplayground.features.container.ContentContainerState
import com.example.madplayground.features.container.api.ContentContainer
import com.example.madplayground.features.container.api.ContentContainer.State
import com.example.madplayground.features.container.api.ContentContainer.ViewModel.Action
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {

        app.settings.themeType
            .onEach { themeType ->

                state.themeType = themeType

            }.launchIn(viewModelScope)

        app.settings.iconographyType
            .onEach { iconographyType ->

                state.iconographyType = iconographyType

            }.launchIn(viewModelScope)

        app.settings.shapeType
            .onEach { shapeType ->

                state.shapeType = shapeType

            }.launchIn(viewModelScope)

        app.settings.navigationLabelVisibility
            .onEach { visibility ->

                state.navigationLabelVisibility = visibility

            }.launchIn(viewModelScope)

    }

    override val actionHandler = Message.Handler<Action> { action ->

        logs.logDebug(
            tag = tag,
            message = "Handling: $action"
        )

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

}