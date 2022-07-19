package com.example.madplayground.features.quotes.controllers

import com.example.madplayground.features.quotes.apis.Quote
import com.example.madplayground.features.quotes.apis.Quotes
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