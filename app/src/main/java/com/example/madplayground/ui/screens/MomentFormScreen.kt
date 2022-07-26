package com.example.madplayground.ui.screens

import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.ui.moments.models.MomentFormUiState
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
interface MomentFormScreen : Screen {

    val scope: CoroutineScope

    val scaffoldState: BackdropScaffoldState

    fun onEvent(event: Event)

    sealed interface Event : Message {

        data class ContentChanged(
            val newContent: String
        ): Event

        data class DateChanged(
            val newDate: String
        ): Event

        data class TimeChanged(
            val newTime: String
        ): Event

        object CancelClicked: Event

        object SaveClicked: Event

        object DismissDialogRequested: Event

        object DiscardChangesClicked: Event

        object BackClicked: Event

    }

    interface ViewModel {

        val state: MomentFormUiState

        val actionHandler: Message.Handler<Command>

        sealed interface Command : Message {

            data class ChangeContent(
                val newContent: String
            ): Command

            data class ChangeDate(
                val newDate: String
            ): Command

            data class ChangeTime(
                val newTime: String
            ): Command

            object SubmitForm: Command

        }

    }

    companion object {

        const val ROUTE: String = "moment_form_screen"

    }

}