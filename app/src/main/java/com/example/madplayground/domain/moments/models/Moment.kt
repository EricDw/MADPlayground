package com.example.madplayground.domain.moments.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import java.util.*

interface Moment {

    val id: Id

    val createdDateTime: LocalDateTime

    val description: String

    val date: LocalDate?

    val time: LocalTime?

    @JvmInline
    value class Id(
        val value: UUID = UUID.randomUUID(),
    )

}