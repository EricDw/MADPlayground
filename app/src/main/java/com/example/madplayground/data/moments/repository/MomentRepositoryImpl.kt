package com.example.madplayground.data.moments.repository

import com.example.madplayground.data.moments.models.MomentsLocalRepository
import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.domain.moments.repository.MomentRepository
import kotlinx.coroutines.flow.Flow

class MomentRepositoryImpl(
    private val localRepository: MomentsLocalRepository
) : MomentRepository {

    override suspend fun addMoment(
        moment: Moment
    ) {
       localRepository.addMoment(
           moment
       )
    }

    override suspend fun retrieveMomentById(
        id: Moment.Id
    ): Moment? {
        return localRepository.retrieveMomentById(id = id)
    }

    override suspend fun retrieveAllMoments(): List<Moment> {
        return localRepository.retrieveAllMoments()
    }

    override suspend fun retrieveAllMomentsFlow(): Flow<List<Moment>> {
        return localRepository.retrieveAllMomentsFlow()
    }

}