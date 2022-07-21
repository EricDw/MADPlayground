package com.example.madplayground.ui.moments.models

import com.example.madplayground.domain.messages.Message
import kotlinx.coroutines.flow.StateFlow

interface MomentFormScreen {

    interface State {

        val description: StateFlow<String>

        val author: StateFlow<String>

        val submitting: StateFlow<Boolean>

        val submitted: StateFlow<Boolean>

    }

    interface ViewModel {

        val state: StateFlow<State>

        val actionHandler: Message.Handler<Action>

        sealed interface Action : Message {

            data class ChangeContent(
                val newContent: String
            ): Action

            data class ChangeAuthor(
                val newAuthor: String
            ): Action

            object SubmitForm: Action

        }

    }

    sealed interface Event : Message {

        data class ContentChanged(
            val newContent: String
        ): Event

        data class AuthorChanged(
            val newAuthor: String
        ): Event

        object NavigationIconClicked: Event

        object CancelClicked: Event

        object SaveClicked: Event

    }

    companion object {

        const val ROUTE: String = "moment_form_screen"

    }

}