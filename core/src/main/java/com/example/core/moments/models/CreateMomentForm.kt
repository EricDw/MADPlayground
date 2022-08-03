package com.example.core.moments.models

interface CreateMomentForm {

    val description: String

    val date: String?

    val time: String?

    interface Builder {

        var description: String

        var date: String?

        var time: String?

    }

}