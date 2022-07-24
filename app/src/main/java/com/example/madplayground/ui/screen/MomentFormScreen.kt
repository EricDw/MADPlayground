package com.example.madplayground.ui.screen

import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import com.example.madplayground.domain.messages.Message
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

    companion object {

        const val ROUTE: String = "moment_form_screen"

    }

}