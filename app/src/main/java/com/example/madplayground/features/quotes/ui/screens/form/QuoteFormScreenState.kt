package com.example.madplayground.features.quotes.ui.screens.form

import com.example.madplayground.features.quotes.ui.screens.form.api.QuoteFormScreen
import kotlinx.coroutines.flow.MutableStateFlow

class QuoteFormScreenState : QuoteFormScreen.State {

    override val content = MutableStateFlow("")

    override val author = MutableStateFlow("")

    override val submitting = MutableStateFlow(false)

    override val submitted = MutableStateFlow(false)

}

fun rememberQuoteFromScreenState(): QuoteFormScreenState {
    return QuoteFormScreenState()
}