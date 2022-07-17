package com.example.madplayground.ui.container.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.features.app.apis.App
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.quotes.controllers.QuoteController
import com.example.madplayground.ui.container.ContentContainerState
import com.example.madplayground.ui.container.api.ContentContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ContentContainerViewModel @Inject constructor(
    private val app: App,
) : ViewModel(), ContentContainer.ViewModel, App by app {

    private val tag = this::class.simpleName

    private var state = ContentContainerState()

    private val _stateFlow: MutableStateFlow<ContentContainer.State> =
        MutableStateFlow(state)

    private val _sideEffect = MutableStateFlow<ContentContainer.SideEffect?>(null)

    override val stateFlow: StateFlow<ContentContainer.State> =
        _stateFlow.asStateFlow()

    override val sideEffect: StateFlow<ContentContainer.SideEffect?> =
        _sideEffect.asStateFlow()

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

    override val eventHandler: Message.Handler<ContentContainer.Event> =
        Message.Handler { event ->
            when (event) {

                ContentContainer.Event.HomeTabClicked          -> {
                    logs.logDebug(
                        tag = tag,
                        message = "Handling event: ${event::class.simpleName}"
                    )

                    _sideEffect.value = ContentContainer.SideEffect.NavigateToHomeTab {
                        _sideEffect.value = null
                    }

                }

                ContentContainer.Event.SettingsTabClicked      -> {

                    logs.logDebug(
                        tag = tag,
                        message = "Handling event: ${event::class.simpleName}"
                    )

                    _sideEffect.value = ContentContainer.SideEffect.NavigateToSettingsTab {
                        _sideEffect.value = null
                    }

                }

                ContentContainer.Event.NavigationButtonClicked -> {

                    logs.logDebug(
                        tag = tag,
                        message = "Handling event: ${event::class.simpleName}"
                    )

                    when (state.screenContext) {

                        ContentContainer.ScreenContext.HOME     -> {
                            /* no-op */
                        }

                        ContentContainer.ScreenContext.SETTINGS -> {

                            logs.logDebug(
                                tag = tag,
                                message = "Navigating Back"
                            )

                            _sideEffect.value = ContentContainer.SideEffect.NavigateBack {
                                _sideEffect.value = null
                            }

                        }

                    }

                }

                ContentContainer.Event.HomeScreenStarted       -> {
                    state.screenContext = ContentContainer.ScreenContext.HOME
                }

                ContentContainer.Event.SettingsScreenStarted   -> {
                    state.screenContext = ContentContainer.ScreenContext.SETTINGS
                }

                ContentContainer.Event.FABClicked              -> {

                    viewModelScope.launch {

                        quotes.addNewQuote(
                            QuoteController(
                                content = "Quote: ${Random.nextInt()}"
                            )
                        )

                        logs.logDebug(
                            tag = tag,
                            message = "Event: $event Handled"
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