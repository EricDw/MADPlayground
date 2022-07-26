package com.example.madplayground.data.moments.repository

import com.example.madplayground.data.moments.mapper.MomentDataMapper
import com.example.madplayground.data.moments.models.LocalMomentDataSource
import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.domain.moments.repository.MomentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MomentRepositoryImpl(
    private val localMomentDataSource: LocalMomentDataSource,
    private val mapper: MomentDataMapper,
) : MomentRepository {

    override suspend fun addMoment(
        moment: Moment,
    ) {
        localMomentDataSource.addMomentData(
            mapper.mapToData(moment = moment)
        )
    }

    override suspend fun retrieveMomentById(
        id: Moment.Id,
    ): Moment? {
        return localMomentDataSource.retrieveMomentDataById(
            id = id.value.toString()
        )?.let(mapper::mapToDomain)
    }

    override suspend fun retrieveAllMoments(): List<Moment> {
        return localMomentDataSource.retrieveAllMomentData().map(mapper::mapToDomain)
    }

    override suspend fun retrieveAllMomentsFlow(): Flow<List<Moment>> {
        return localMomentDataSource.retrieveAllMomentDataFlow().map {
            it.map(mapper::mapToDomain)
        }
    }

}