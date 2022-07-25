package com.example.madplayground.domain.moments.repository

import com.example.madplayground.domain.moments.models.Moment
import kotlinx.coroutines.flow.Flow

interface MomentRepository {

    suspend fun addMoment(
        moment: Moment,
    )

    suspend fun retrieveMomentById(
        id: Moment.Id,
    ): Moment?

    suspend fun retrieveAllMoments(): List<Moment>

    suspend fun retrieveAllMomentsFlow(): Flow<List<Moment>>

}