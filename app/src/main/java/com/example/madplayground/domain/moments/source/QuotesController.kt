package com.example.madplayground.domain.moments.source

import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.domain.moments.models.Moments
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MomentsController : Moments {

    private val _momentOfTheDay = MutableStateFlow<Moment?>(
        null
    )

    override val momentOfTheDay: StateFlow<Moment?> =
        _momentOfTheDay.asStateFlow()

    override suspend fun addNewMoment(
        content: String,
        author: String?,
    ) {
        _momentOfTheDay.value = MomentController(
            description = content,
            author = author
        )
    }
}