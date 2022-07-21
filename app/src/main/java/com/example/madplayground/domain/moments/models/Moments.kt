package com.example.madplayground.domain.moments.models

import kotlinx.coroutines.flow.StateFlow

interface Moments {

    val momentOfTheDay: StateFlow<Moment?>

    suspend fun addNewMoment(
        content: String,
        author: String? = null
    )

}
