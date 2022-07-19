package com.example.madplayground.features.quotes.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.madplayground.features.quotes.apis.Quote
import java.util.*

data class QuoteState(
    override var id: String = UUID.randomUUID().toString(),
    override var content: String = "",
    override val author: String? = null
) : Quote.State

@Composable
fun rememberQuoteState(
    initializer: QuoteState.() -> Unit = {},
): QuoteState = remember {
    QuoteState().apply(initializer)
}
