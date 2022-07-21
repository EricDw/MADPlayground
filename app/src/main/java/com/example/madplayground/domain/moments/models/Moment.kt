package com.example.madplayground.domain.moments.models

import kotlinx.datetime.LocalDateTime

interface Moment {

    val id: Id

    val description: String

    val date: LocalDateTime

    @JvmInline
    value class Id(val value: String)

}