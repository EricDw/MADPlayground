package com.example.data.moments.repository

import com.example.core.moments.repository.MomentRepository
import com.example.data.moments.mapper.MomentDataMapper
import com.example.data.moments.models.LocalMomentDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MomentRepositoryImpl(
    private val localMomentDataSource: LocalMomentDataSource,
    private val mapper: MomentDataMapper,
) : MomentRepository {

    override suspend fun addMoment(
        moment: com.example.core.moments.models.Moment,
    ) {
        localMomentDataSource.addMomentData(
            mapper.mapToData(moment = moment)
        )
    }

    override suspend fun retrieveMomentById(
        id: com.example.core.moments.models.Moment.Id,
    ): com.example.core.moments.models.Moment? {
        return localMomentDataSource.retrieveMomentDataById(
            id = id.value.toString()
        )?.let(mapper::mapToDomain)
    }

    override suspend fun retrieveAllMoments(): List<com.example.core.moments.models.Moment> {
        return localMomentDataSource.retrieveAllMomentData().map(mapper::mapToDomain)
    }

    override suspend fun retrieveAllMomentsFlow(): Flow<List<com.example.core.moments.models.Moment>> {
        return localMomentDataSource.retrieveAllMomentDataFlow().map {
            it.map(mapper::mapToDomain)
        }
    }

}