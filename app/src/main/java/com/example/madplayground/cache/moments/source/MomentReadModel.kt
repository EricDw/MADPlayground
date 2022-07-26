package com.example.madplayground.cache.moments.source

import com.example.madplayground.domain.moments.models.Moment
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

data class MomentReadModel(
    override val id: Moment.Id,
    override val createdDateTime: LocalDateTime,
    override val description: String,
    override val date: LocalDate?,
    override val time: LocalTime?,
) : Moment