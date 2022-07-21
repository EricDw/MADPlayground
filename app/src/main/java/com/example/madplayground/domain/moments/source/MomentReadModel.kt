package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.models.Moment
import kotlinx.datetime.LocalDateTime
import java.util.UUID

data class MomentReadModel(
    override val id: Moment.Id,
    override val description: String,
    override val date: LocalDateTime,
) : Moment