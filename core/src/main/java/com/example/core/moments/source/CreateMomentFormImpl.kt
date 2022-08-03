package com.example.core.moments.source

internal data class CreateMomentFormImpl(
    override var description: String,
    override val date: String?,
    override val time: String?
) : com.example.core.moments.models.CreateMomentForm