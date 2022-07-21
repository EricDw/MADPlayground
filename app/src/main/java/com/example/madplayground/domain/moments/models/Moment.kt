package com.example.madplayground.domain.moments.models

interface Moment {

    val id: String

    val description: String

    val author: String?

    interface State {

        val id: String

        val description: String

        val author: String?

    }

}