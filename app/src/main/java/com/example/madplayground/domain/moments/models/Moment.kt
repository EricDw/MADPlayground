package com.example.madplayground.domain.moments.models

interface Moment {

    val id: String

    val content: String

    val author: String?

    interface State {

        val id: String

        val content: String

        val author: String?

    }

}