package com.example.madplayground.domain.quotes.source

import com.example.madplayground.domain.quotes.models.Quote
import com.example.madplayground.domain.quotes.models.Quotes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuotesController : Quotes {

    private val _quoteOfTheDay = MutableStateFlow<Quote?>(
        null
    )

    override val quoteOfTheDay: StateFlow<Quote?> =
        _quoteOfTheDay.asStateFlow()

    override suspend fun addNewQuote(
        content: String,
        author: String?,
    ) {
        _quoteOfTheDay.value = QuoteController(
            content = content,
            author = author
        )
    }
}