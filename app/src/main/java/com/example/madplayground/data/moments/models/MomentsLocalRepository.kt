package com.example.madplayground.data.moments.models

import com.example.madplayground.domain.moments.models.Moment
import kotlinx.coroutines.flow.Flow

interface MomentsLocalRepository {

    suspend fun addMoment(
        moment: Moment,
    )

    suspend fun retrieveMomentById(
        id: Moment.Id,
    ): Moment?

    suspend fun retrieveAllMoments(): List<Moment>

    suspend fun retrieveAllMomentsFlow(): Flow<List<Moment>>

}