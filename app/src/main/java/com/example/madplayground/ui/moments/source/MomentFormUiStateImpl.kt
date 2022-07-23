package com.example.madplayground.ui.moments.source

import com.example.madplayground.ui.moments.models.MomentFormUiState
import kotlinx.coroutines.flow.MutableStateFlow

class MomentFormUiStateImpl : MomentFormUiState {

    override val description = MutableStateFlow("")

    override val date = MutableStateFlow("")

    override val time = MutableStateFlow("")

    override val submitting = MutableStateFlow(false)

    override val submitted = MutableStateFlow(false)

}

fun rememberMomentFromScreenState(): MomentFormUiStateImpl {
    return MomentFormUiStateImpl()
}