package com.example.madplayground.domain.quotes.models

import kotlinx.coroutines.flow.StateFlow

interface Quotes {

    val quoteOfTheDay: StateFlow<Quote?>

    suspend fun addNewQuote(
        content: String,
        author: String? = null
    )

}
