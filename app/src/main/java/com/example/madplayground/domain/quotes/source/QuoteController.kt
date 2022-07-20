package com.example.madplayground.domain.quotes.source

import com.example.madplayground.domain.quotes.models.Quote
import java.util.UUID

data class QuoteController(
    override val content: String,
    override val id: String = UUID.randomUUID().toString(),
    override val author: String? = null,
) : Quote