package com.example.madplayground.features.quotes.apis

import kotlinx.coroutines.flow.StateFlow

interface Quotes {

    val quoteOfTheDay: StateFlow<Quote?>

    suspend fun addNewQuote(
        quote: Quote
    )

}
