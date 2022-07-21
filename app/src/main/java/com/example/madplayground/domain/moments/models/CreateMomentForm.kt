package com.example.madplayground.domain.moments.models

interface CreateMomentForm {

    val description: String

    interface Builder {

        var description: String

    }

}