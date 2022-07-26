package com.example.madplayground.ui.container.source

import com.example.madplayground.common.logs.models.Logs
import com.example.madplayground.domain.settings.usecases.RetrieveIconographyTypeUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveNavigationLabelVisibilityUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveShapeTypeUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveThemeTypeUseCase
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.models.ContentContainer.ViewModel.Command
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

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

    private val _state = ContentContainerState()

    override var state = _state

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

            _state.themeType.update { themeType }

        }.launchIn(scope)

        retrieveIconographyTypeUseCase().onEach { iconographyType ->

            logDebug(
                tag = tag,
                message = "Iconography Type changed to: $iconographyType"
            )

            _state.iconographyType.update { iconographyType }

        }.launchIn(scope)

        retrieveShapeTypeUseCase().onEach { shapeType ->

            logDebug(
                tag = tag,
                message = "ShapeType changed to: $shapeType"
            )

            _state.shapeType.update { shapeType }

        }.launchIn(scope)

        retrieveNavigationLabelVisibilityUseCase().onEach { visibility ->

            logDebug(
                tag = tag,
                message = "NavigationLabelVisibility changed to: $visibility"
            )

            _state.navigationLabelVisibility.update { visibility }

        }.launchIn(scope)

        logDebug(
            tag = tag,
            message = "Initialized"
        )

    }

    override val commandHandler: (Command) -> Unit = { action ->

        logError(
            tag = tag,
            message = "Action: $action Not Handled!"
        )

    }

}