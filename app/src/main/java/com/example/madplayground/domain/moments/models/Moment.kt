package com.example.madplayground.domain.moments.models

import kotlinx.datetime.LocalDateTime
import java.util.UUID

interface Moment {

    val id: Id

    val description: String

    val date: LocalDateTime

    @JvmInline
    value class Id(
        val value: String = UUID.randomUUID().toString(),
    )

}