package com.example.madplayground.domain.moments.repository

import com.example.madplayground.domain.moments.models.Moment
import kotlinx.coroutines.flow.StateFlow

interface MomentRepository {

    suspend fun addMoment(
        moment: Moment
    )

    suspend fun getMomentById(
        id: Moment.Id
    ): Moment?

    suspend fun getAllMoments(): StateFlow<List<Moment>>

}