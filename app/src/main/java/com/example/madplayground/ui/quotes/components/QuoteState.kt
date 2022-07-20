package com.example.madplayground.ui.quotes.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.madplayground.domain.quotes.models.Quote
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
