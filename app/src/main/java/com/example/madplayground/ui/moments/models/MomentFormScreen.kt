package com.example.madplayground.ui.moments.models

import com.example.madplayground.domain.messages.Message

interface MomentFormScreen {

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

    }

    companion object {

        const val ROUTE: String = "moment_form_screen"

    }

}