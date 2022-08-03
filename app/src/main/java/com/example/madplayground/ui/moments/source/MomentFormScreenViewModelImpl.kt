package com.example.madplayground.ui.moments.source

import com.example.core.moments.source.buildCreateMomentForm
import com.example.core.moments.usecases.CreateMomentUseCase
import com.example.madplayground.ui.moments.models.MomentFormScreen
import com.example.madplayground.ui.moments.models.MomentFormScreen.ViewModel.Command
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MomentFormScreenViewModelImpl(
    private val logs: com.example.common.logs.models.Logs,
    private val createMomentUseCase: CreateMomentUseCase,
    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.Main.immediate + SupervisorJob()
    ),
) : MomentFormScreen.ViewModel, com.example.common.logs.models.Logs by logs {

    private val tag = this::class.simpleName

    private val _state = MomentFormUiStateImpl()

    override val state = _state

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

    override val commandHandler: (Command) -> Unit = { theCommand ->

        logDebug(
            tag = tag,
            message = "Received: $theCommand"
        )

        when (theCommand) {

            is Command.ChangeContent -> {

                _state.description.update {
                    theCommand.newContent
                }

            }

            is Command.ChangeDate    -> {

                _state.date.update {
                    theCommand.newDate
                }

            }

            is Command.ChangeTime    -> {

                _state.time.update {
                    theCommand.newTime
                }

            }

            Command.SubmitForm       -> {

                scope.launch {

                    val form = buildCreateMomentForm {
                        description = _state.description.value
                        date = _state.date.value.takeUnless { it.isBlank() }
                        time = _state.time.value.takeUnless { it.isBlank() }
                    }

                    createMomentUseCase
                        .invoke(form)
                        .collect { theResult ->

                            when (theResult) {

                                is CreateMomentUseCase.Result.Running  -> {

                                    _state.submitting.update {
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

                                    _state.submitted.update {
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