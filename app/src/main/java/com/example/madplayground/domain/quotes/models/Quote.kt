package com.example.madplayground.domain.quotes.models

interface Quote {

    val id: String

    val content: String

    val author: String?

    interface State {

        val id: String

        val content: String

        val author: String?

    }

}