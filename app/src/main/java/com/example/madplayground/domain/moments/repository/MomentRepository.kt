package com.example.madplayground.domain.moments.repository

import com.example.madplayground.domain.moments.models.Moment
import kotlinx.coroutines.flow.Flow

interface MomentRepository {

    suspend fun addMoment(
        moment: Moment,
    )

    suspend fun getMomentById(
        id: Moment.Id,
    ): Moment?

    suspend fun getAllMoments(): List<Moment>

    suspend fun getAllMomentsFlow(): Flow<List<Moment>>

}