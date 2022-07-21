package com.example.madplayground.domain.moments.repository

import com.example.madplayground.domain.moments.models.Moment
import kotlinx.coroutines.flow.StateFlow

interface MomentRepository {

    suspend fun addMoment(
        moment: Moment
    )

    suspend fun getMoment(): StateFlow<Moment?>

}