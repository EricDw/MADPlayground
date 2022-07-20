package com.example.madplayground.ui.container.source

import com.example.madplayground.app.models.App
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.models.ContentContainer.State
import com.example.madplayground.ui.container.models.ContentContainer.ViewModel.Action
import com.example.madplayground.domain.messages.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*

class ContentContainerViewModel(
    private val app: App,
    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.Main.immediate + SupervisorJob()
    ),
) : ContentContainer.ViewModel, App by app {

    private val tag = this::class.simpleName

    private var state = ContentContainerState()

    private val _stateFlow: MutableStateFlow<State> =
        MutableStateFlow(state)

    override val stateFlow: StateFlow<State> =
        _stateFlow.asStateFlow()

    init {

        logs.logDebug(
            tag = tag,
            message = "Initializing"
        )

        settings.themeType
            .onEach { themeType ->

                logs.logDebug(
                    tag = tag,
                    message = "ThemeType changed to: $themeType"
                )

                state.themeType = themeType

            }.launchIn(scope)

        settings.iconographyType
            .onEach { iconographyType ->

                logs.logDebug(
                    tag = tag,
                    message = "Iconography Type changed to: $iconographyType"
                )

                state.iconographyType = iconographyType

            }.launchIn(scope)

        settings.shapeType
            .onEach { shapeType ->

                logs.logDebug(
                    tag = tag,
                    message = "ShapeType changed to: $shapeType"
                )

                state.shapeType = shapeType

            }.launchIn(scope)

        settings.navigationLabelVisibility
            .onEach { visibility ->

                logs.logDebug(
                    tag = tag,
                    message = "NavigationLabelVisibility changed to: $visibility"
                )

                state.navigationLabelVisibility = visibility

            }.launchIn(scope)

        logs.logDebug(
            tag = tag,
            message = "Initialized"
        )

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

        }

    }

}