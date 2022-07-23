package com.example.madplayground.ui.settings.source

import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.settings.usecases.*
import com.example.madplayground.ui.settings.models.SettingsScreen
import com.example.madplayground.ui.settings.models.SettingsScreen.State
import com.example.madplayground.ui.settings.models.SettingsScreen.ViewModel.Action
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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

    private val settingsScreenState = SettingsScreenState()

    private val _stateFlow = MutableStateFlow(settingsScreenState)

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
                message = "ThemeType Changed too: $themeType"
            )

            settingsScreenState.themeType.update { themeType }

        }.launchIn(scope)

        retrieveIconographyTypeUseCase().onEach { iconographyType ->

            logDebug(
                tag = tag,
                message = "IconographyType Changed too: $iconographyType"
            )

            settingsScreenState.iconType.update { iconographyType }

        }.launchIn(scope)

        retrieveShapeTypeUseCase().onEach { shapeType ->

            logDebug(
                tag = tag,
                message = "ShapeType Changed too: $shapeType"
            )

            settingsScreenState.shapeType.update { shapeType }

        }.launchIn(scope)

        retrieveNavigationLabelVisibilityUseCase().onEach { visibility ->

            logDebug(
                tag = tag,
                message = "NavigationLabelVisibility Changed too: $visibility"
            )

            settingsScreenState.navigationLabelVisibility.update { visibility }

        }.launchIn(scope)

        logDebug(
            tag = tag,
            message = "Initialized"
        )

    }

    override val actionHandler: Message.Handler<Action> = Message.Handler { theAction ->

        logDebug(
            tag = tag,
            message = "Received: $theAction"
        )

        when (theAction) {

            Action.CycleThemeType       -> {

                scope.launch {

                    cycleThemeTypeUseCase()

                }

            }

            Action.CycleIconographyType -> {

                scope.launch {

                    cycleIconographyTypeUseCase()

                }

            }

            Action.CycleShapeType       -> {

                scope.launch {

                    cycleShapeTypeUseCase()

                }

            }

            Action.CycleLabelVisibility -> {

                scope.launch {

                    cycleNavigationLabelVisibilityUseCase()

                }
            }

        }

    }

}