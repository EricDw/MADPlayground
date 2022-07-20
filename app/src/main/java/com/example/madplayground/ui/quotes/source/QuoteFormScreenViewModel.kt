package com.example.madplayground.ui.quotes.source

import com.example.madplayground.app.models.App
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.ui.quotes.models.QuoteFormScreen.State
import com.example.madplayground.ui.quotes.models.QuoteFormScreen.ViewModel
import com.example.madplayground.ui.quotes.models.QuoteFormScreen.ViewModel.Action
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuoteFormScreenViewModel(
    private val app: App,
    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.Main.immediate + SupervisorJob()
    ),
) : ViewModel, App by app {

    private val tag = this::class.simpleName

    private val screenState = QuoteFormScreenState()

    private val _state = MutableStateFlow(screenState)

    override val state: StateFlow<State> =
        _state.asStateFlow()

    init {

        logs.logDebug(
            tag = tag,
            message = "Initializing"
        )

        logs.logDebug(
            tag = tag,
            message = "Initialized"
        )

    }

    override val actionHandler: Message.Handler<Action> = Message.Handler { theAction ->

        logs.logDebug(
            tag = tag,
            message = "Received: $theAction"
        )

        when (theAction) {

            is Action.ChangeContent -> {

                screenState.content.update {
                    theAction.newContent
                }

            }

            is Action.ChangeAuthor  -> {

                screenState.author.update {
                    theAction.newAuthor
                }

            }

            Action.SubmitForm       -> {

                screenState.submitting.update {
                    true
                }

                scope.launch {

                    delay(1000)

                    quotes.addNewQuote(
                        screenState.content.value,
                        screenState.author.value.takeIf { it.isNotBlank() }
                    )

                    screenState.submitted.update {
                        true
                    }

                }

            }

        }

    }

}