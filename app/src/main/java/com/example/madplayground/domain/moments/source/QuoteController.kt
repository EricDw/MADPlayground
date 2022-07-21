package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.models.Moment
import java.util.UUID

data class MomentController(
    override val description: String,
    override val id: String = UUID.randomUUID().toString(),
    override val author: String? = null,
) : Moment