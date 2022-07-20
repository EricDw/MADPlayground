package com.example.madplayground.ui.quotes.source

import com.example.madplayground.ui.quotes.models.QuoteFormScreen
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