package com.example.madplayground.features.quotes.controllers

import com.example.madplayground.features.quotes.apis.Quote
import com.example.madplayground.features.quotes.apis.Quotes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

data class QuoteController(
    override val content: String,
    override val id: String = UUID.randomUUID().toString(),
    override val author: String? = null,
) : Quote