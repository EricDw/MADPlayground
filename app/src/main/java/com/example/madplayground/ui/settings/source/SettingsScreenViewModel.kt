package com.example.madplayground.ui.settings.source

import com.example.madplayground.common.logs.models.Logs
import com.example.madplayground.domain.settings.usecases.*
import com.example.madplayground.ui.settings.models.SettingsScreen
import com.example.madplayground.ui.settings.models.SettingsScreen.State
import com.example.madplayground.ui.settings.models.SettingsScreen.ViewModel.Command
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsScreenViewModel(
    private val logs: Logs,
    retrieveThemeTypeUseCase: RetrieveThemeTypeUseCase,
    retrieveIconographyTypeUseCase: RetrieveIconographyTypeUseCase,
    retrieveShapeTypeUseCase: RetrieveShapeTypeUseCase,
    retrieveNavigationLabelVisibilityUseCase: RetrieveNavigationLabelVisibilityUseCase,
    cycleThemeTypeUseCase: CycleThemeTypeUseCase,
    cycleIconographyTypeUseCase: CycleIconographyTypeUseCase,
    cycleShapeTypeUseCase: CycleShapeTypeUseCase,
    cycleNavigationLabelVisibilityUseCase: CycleNavigationLabelVisibilityUseCase,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate),
) : SettingsScreen.ViewModel, Logs by logs {

    private val tag = this::class.simpleName

    private val _state = SettingsScreenState()

    override val state: State = _state

    init {

        logDebug(
            tag = tag,
            message = "Initializing"
        )

        retrieveThemeTypeUseCase().onEach { themeType ->

            logDebug(
                tag = tag,
                message = "ThemeType Changed too: $themeType"
            )

            _state.themeType.update { themeType }

        }.launchIn(scope)

        retrieveIconographyTypeUseCase().onEach { iconographyType ->

            logDebug(
                tag = tag,
                message = "IconographyType Changed too: $iconographyType"
            )

            _state.iconType.update { iconographyType }

        }.launchIn(scope)

        retrieveShapeTypeUseCase().onEach { shapeType ->

            logDebug(
                tag = tag,
                message = "ShapeType Changed too: $shapeType"
            )

            _state.shapeType.update { shapeType }

        }.launchIn(scope)

        retrieveNavigationLabelVisibilityUseCase().onEach { visibility ->

            logDebug(
                tag = tag,
                message = "NavigationLabelVisibility Changed too: $visibility"
            )

            _state.navigationLabelVisibility.update { visibility }

        }.launchIn(scope)

        logDebug(
            tag = tag,
            message = "Initialized"
        )

    }

    override val commandHandler: (Command) -> Unit = { theCommand ->

        logDebug(
            tag = tag,
            message = "Received: $theCommand"
        )

        when (theCommand) {

            Command.CycleThemeType       -> {

                scope.launch {

                    cycleThemeTypeUseCase()

                }

            }

            Command.CycleIconographyType -> {

                scope.launch {

                    cycleIconographyTypeUseCase()

                }

            }

            Command.CycleShapeType       -> {

                scope.launch {

                    cycleShapeTypeUseCase()

                }

            }

            Command.CycleLabelVisibility -> {

                scope.launch {

                    cycleNavigationLabelVisibilityUseCase()

                }
            }

        }

    }

}