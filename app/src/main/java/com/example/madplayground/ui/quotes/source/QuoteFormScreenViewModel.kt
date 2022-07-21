package com.example.madplayground.ui.quotes.source

import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.moments.source.buildCreateMomentForm
import com.example.madplayground.domain.moments.usecases.CreateMomentUseCase
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.moments.models.MomentFormScreen.State
import com.example.madplayground.ui.moments.models.MomentFormScreen.ViewModel
import com.example.madplayground.ui.moments.models.MomentFormScreen.ViewModel.Action
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MomentFormScreenViewModel(
    private val logs: Logs,
    private val createMomentUseCase: CreateMomentUseCase,
    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.Main.immediate + SupervisorJob()
    ),
) : ViewModel, Logs by logs {

    private val tag = this::class.simpleName

    private val screenState = MomentFormScreenState()

    private val _state = MutableStateFlow(screenState)

    override val state: StateFlow<State> =
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

            is Action.ChangeAuthor  -> {

                screenState.author.update {
                    theAction.newAuthor
                }

            }

            Action.SubmitForm       -> {

                scope.launch {

                    val form = buildCreateMomentForm {
                        description = screenState.description.value
                    }

                    createMomentUseCase
                        .invoke(form)
                        .collect { theResult ->

                            when (theResult) {

                                is CreateMomentUseCase.Result.Complete -> {

                                    screenState.submitted.update {
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

                                is CreateMomentUseCase.Result.Running  -> {

                                    screenState.submitting.update {
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