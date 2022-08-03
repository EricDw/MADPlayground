package com.example.core.moments.repository

import kotlinx.coroutines.flow.Flow

interface MomentRepository {

    suspend fun addMoment(
        moment: com.example.core.moments.models.Moment,
    )

    suspend fun retrieveMomentById(
        id: com.example.core.moments.models.Moment.Id,
    ): com.example.core.moments.models.Moment?

    suspend fun retrieveAllMoments(): List<com.example.core.moments.models.Moment>

    suspend fun retrieveAllMomentsFlow(): Flow<List<com.example.core.moments.models.Moment>>

}