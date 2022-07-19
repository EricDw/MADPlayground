package com.example.madplayground.features.quotes.apis

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