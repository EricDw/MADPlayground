package com.example.madplayground.ui.home.source

import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.domain.moments.usecases.RetrieveAllMomentUseCase
import com.example.madplayground.ui.screen.HomeScreen
import com.example.madplayground.ui.moments.models.MomentUiState
import com.example.madplayground.ui.moments.source.MomentUiStateImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*

class HomeScreenViewModel(
    private val logs: Logs,
    retrieveAllMomentUseCase: RetrieveAllMomentUseCase,
    scope: CoroutineScope? = null,
) : HomeScreen.ViewModel, Logs by logs {

    private val scope: CoroutineScope =
        scope ?: CoroutineScope(
            context = Dispatchers.Main.immediate + SupervisorJob()
        )

    private val tag = this::class.simpleName

    private val screenState = HomeScreenState()

    private val _state = MutableStateFlow(screenState)

    override val state: StateFlow<HomeScreen.State> =
        _state.asStateFlow()

    init {

        logDebug(
            tag = tag,
            message = "Initializing"
        )

        retrieveAllMomentUseCase.invoke().onEach { result ->

            logDebug(
                tag = tag,
                message = "On-Result: $result"
            )

            when (result) {

                is RetrieveAllMomentUseCase.Result.Complete -> {

                    result.moments.map { moments ->

                        moments.map(::toUiState)

                    }.onEach { uiStates ->

                        screenState.moments.clear()
                        screenState.moments.addAll(uiStates)

                    }.launchIn(this.scope)

                }

                is RetrieveAllMomentUseCase.Result.Error    -> {

                    logError(
                        tag = tag,
                        message = "Error retrieving Moments: $result"
                    )

                }

                is RetrieveAllMomentUseCase.Result.Running  -> {

                }

            }

        }.launchIn(scope = this.scope)

        logDebug(
            tag = tag,
            message = "Initialized"
        )

    }

    private fun toUiState(moment: Moment): MomentUiState {
        return with(moment) {
            MomentUiStateImpl(
                id = id.value.toString(),
                description = description
            )
        }
    }

}