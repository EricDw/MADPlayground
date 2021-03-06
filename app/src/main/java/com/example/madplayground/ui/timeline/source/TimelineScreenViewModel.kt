package com.example.madplayground.ui.timeline.source

import com.example.core.moments.usecases.RetrieveAllMomentUseCase
import com.example.madplayground.ui.moments.mapper.MomentUIMapper
import com.example.madplayground.ui.timeline.models.TimelineScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class TimelineScreenViewModel(
    private val logs: com.example.common.logs.models.Logs,
    private val momentUIMapper: MomentUIMapper,
    retrieveAllMomentUseCase: RetrieveAllMomentUseCase,
    scope: CoroutineScope? = null,
) : TimelineScreen.ViewModel, com.example.common.logs.models.Logs by logs {

    private val scope: CoroutineScope =
        scope ?: CoroutineScope(
            context = Dispatchers.Main.immediate + SupervisorJob()
        )

    private val tag = this::class.simpleName

    private val _state = TimelineScreenState()

    override val state: TimelineScreen.State = _state

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

                        moments.sortedBy {
                            it.createdDateTime
                        }.map(momentUIMapper::mapToUIState)

                    }.onEach { uiStates ->

                        _state.moments.clear()
                        _state.moments.addAll(uiStates)

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

}