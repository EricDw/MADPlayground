package com.example.madplayground.ui.moments.models

import com.example.madplayground.domain.messages.Message
import kotlinx.coroutines.flow.StateFlow

interface MomentFormViewModel {

        val state: StateFlow<MomentFormUiState>

        val actionHandler: Message.Handler<Action>

        sealed interface Action : Message {

            data class ChangeContent(
                val newContent: String
            ): Action

            data class ChangeDate(
                val newDate: String
            ): Action

            data class ChangeTime(
                val newTime: String
            ): Action

            object SubmitForm: Action

        }

    }
