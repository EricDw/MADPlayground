package com.example.madplayground.ui.container.source

import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.settings.usecases.RetrieveIconographyTypeUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveNavigationLabelVisibilityUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveShapeTypeUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveThemeTypeUseCase
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.models.ContentContainer.State
import com.example.madplayground.ui.container.models.ContentContainer.ViewModel.Action
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*

class ContentContainerViewModel(
    private val logs: Logs,
    retrieveThemeTypeUseCase: RetrieveThemeTypeUseCase,
    retrieveIconographyTypeUseCase: RetrieveIconographyTypeUseCase,
    retrieveShapeTypeUseCase: RetrieveShapeTypeUseCase,
    retrieveNavigationLabelVisibilityUseCase: RetrieveNavigationLabelVisibilityUseCase,
    scope: CoroutineScope = CoroutineScope(
        Dispatchers.Main.immediate + SupervisorJob()
    ),
) : ContentContainer.ViewModel, Logs by logs {

    private val tag = this::class.simpleName

    private var state = ContentContainerState()

    private val _stateFlow: MutableStateFlow<State> =
        MutableStateFlow(state)

    override val stateFlow: StateFlow<State> =
        _stateFlow.asStateFlow()

    init {

        logDebug(
            tag = tag,
            message = "Initializing"
        )

        retrieveThemeTypeUseCase().onEach { themeType ->

            logDebug(
                tag = tag,
                message = "ThemeType changed to: $themeType"
            )

            state.themeType = themeType

        }.launchIn(scope)

        retrieveIconographyTypeUseCase().onEach { iconographyType ->

            logDebug(
                tag = tag,
                message = "Iconography Type changed to: $iconographyType"
            )

            state.iconographyType = iconographyType

        }.launchIn(scope)

        retrieveShapeTypeUseCase().onEach { shapeType ->

            logDebug(
                tag = tag,
                message = "ShapeType changed to: $shapeType"
            )

            state.shapeType = shapeType

        }.launchIn(scope)

        retrieveNavigationLabelVisibilityUseCase().onEach { visibility ->

            logDebug(
                tag = tag,
                message = "NavigationLabelVisibility changed to: $visibility"
            )

            state.navigationLabelVisibility = visibility

        }.launchIn(scope)

        logDebug(
            tag = tag,
            message = "Initialized"
        )

    }

    override val actionHandler = Message.Handler<Action> { action ->

        logError(
            tag = tag,
            message = "Action: $action Not Handled!"
        )

    }

}