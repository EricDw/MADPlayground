package com.example.madplayground.ui.moments.models

interface MomentFormScreen {

    sealed interface Event {

        data class ContentChanged(
            val newContent: String,
        ) : Event

        data class DateChanged(
            val newDate: String,
        ) : Event

        data class TimeChanged(
            val newTime: String,
        ) : Event


        object SaveClicked : Event

        object DismissDialogRequested : Event

        object DiscardChangesClicked : Event

        object BackClicked : Event

    }

    interface ViewModel {

        val state: MomentFormUiState

        val commandHandler: (Command) -> Unit

        sealed interface Command {

            data class ChangeContent(
                val newContent: String,
            ) : Command

            data class ChangeDate(
                val newDate: String,
            ) : Command

            data class ChangeTime(
                val newTime: String,
            ) : Command

            object SubmitForm : Command

        }

    }

    companion object {

        const val ROUTE: String = "moment_form_screen"

    }

}