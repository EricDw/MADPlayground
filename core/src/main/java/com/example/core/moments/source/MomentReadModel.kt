package com.example.core.moments.source

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

data class MomentReadModel(
    override val id: com.example.core.moments.models.Moment.Id,
    override val createdDateTime: LocalDateTime,
    override val description: String,
    override val date: LocalDate?,
    override val time: LocalTime?,
) : com.example.core.moments.models.Moment