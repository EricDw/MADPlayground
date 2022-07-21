package com.example.madplayground.ui.quotes.source

import com.example.madplayground.ui.moments.models.MomentFormScreen
import kotlinx.coroutines.flow.MutableStateFlow

class MomentFormScreenState : MomentFormScreen.State {

    override val description = MutableStateFlow("")

    override val author = MutableStateFlow("")

    override val submitting = MutableStateFlow(false)

    override val submitted = MutableStateFlow(false)

}

fun rememberMomentFromScreenState(): MomentFormScreenState {
    return MomentFormScreenState()
}