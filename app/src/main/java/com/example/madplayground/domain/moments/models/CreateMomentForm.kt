package com.example.madplayground.domain.moments.models

interface CreateMomentForm {

    val description: String

    val date: String

    interface Builder {

        var description: String

        var date: String

    }

}