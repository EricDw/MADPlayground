package com.example.madplayground.data.moments.source

import com.example.madplayground.data.moments.models.MomentData

data class MomentDataImpl(
    override val id: String,
    override val createdDateTime: String,
    override val description: String,
    override val date: String?,
    override val time: String?,
) : MomentData