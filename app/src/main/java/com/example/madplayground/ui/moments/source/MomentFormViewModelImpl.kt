package com.example.madplayground.ui.moments.source

import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.moments.source.buildCreateMomentForm
import com.example.madplayground.domain.moments.usecases.CreateMomentUseCase
import com.example.madplayground.ui.moments.models.MomentFormUiState
import com.example.madplayground.ui.moments.models.MomentFormViewModel
import com.example.madplayground.ui.moments.models.MomentFormViewModel.Action
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MomentFormViewModelImpl(
    private val logs: Logs,
    private val createMomentUseCase: CreateMomentUseCase,
    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.Main.immediate + SupervisorJob()
    ),
) : MomentFormViewModel, Logs by logs {

    private val tag = this::class.simpleName

    private val screenState = MomentFormUiStateImpl()

    private val _state = MutableStateFlow(screenState)

    override val state: StateFlow<MomentFormUiState> =
        _state.asStateFlow()

    init {

        logDebug(
            tag = tag,
            message = "Initializing"
        )

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

            is Action.ChangeContent -> {

                screenState.description.update {
                    theAction.newContent
                }

            }

            is Action.ChangeDate    -> {

                screenState.date.update {
                    theAction.newDate
                }

            }

            is Action.ChangeTime    -> {

                screenState.time.update {
                    theAction.newTime
                }

            }

            Action.SubmitForm       -> {

                scope.launch {

                    val form = buildCreateMomentForm {
                        description = screenState.description.value
                        date = screenState.date.value
                        time = screenState.time.value
                    }

                    createMomentUseCase
                        .invoke(form)
                        .collect { theResult ->

                            when (theResult) {

                                is CreateMomentUseCase.Result.Running  -> {

                                    screenState.submitting.update {
                                        true
                                    }

                                }

                                is CreateMomentUseCase.Result.Error    -> {

                                    logError(
                                        tag = tag,
                                        message = "Error not handled",
                                        error = theResult.cause
                                    )

                                }

                                is CreateMomentUseCase.Result.Complete -> {

                                    screenState.submitted.update {
                                        true
                                    }

                                }

                            }

                        }

                }

            }

        }

    }

}