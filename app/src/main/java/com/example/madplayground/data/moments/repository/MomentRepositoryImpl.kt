package com.example.madplayground.data.moments.repository

import com.example.madplayground.data.moments.models.MomentsLocalRepository
import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.domain.moments.repository.MomentRepository
import kotlinx.coroutines.flow.*

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

    override suspend fun getMomentById(
        id: Moment.Id
    ): Moment? {
        return localRepository.getMomentById(id = id)
    }

    override suspend fun getAllMoments(): List<Moment> {
        return localRepository.getAllMoments()
    }

    override suspend fun getAllMomentsFlow(): Flow<List<Moment>> {
        return localRepository.getAllMomentsFlow()
    }

}