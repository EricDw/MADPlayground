package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.models.CreateMomentForm

internal data class CreateMomentFormImpl(
    override var description: String,
    override val date: String?,
    override val time: String?
) : CreateMomentForm