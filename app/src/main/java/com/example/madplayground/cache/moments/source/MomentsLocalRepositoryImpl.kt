package com.example.madplayground.cache.moments.source

import com.example.madplayground.cache.moments.mapper.MomentCacheMapper
import com.example.madplayground.cache.moments.models.MomentDao
import com.example.madplayground.data.moments.models.MomentsLocalRepository
import com.example.madplayground.domain.moments.models.Moment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MomentsLocalRepositoryImpl(
    private val dao: MomentDao,
    private val mapper: MomentCacheMapper,
) : MomentsLocalRepository {

    override suspend fun addMoment(
        moment: Moment,
    ) {

        val entity = mapper.mapToEntity(
            moment
        )

        dao.insertEntity(entity = entity)
    }

    override suspend fun retrieveMomentById(
        id: Moment.Id,
    ): Moment? {
        return dao.retrieveById(uid = id.value.toString())?.let(mapper::mapToDomain)
    }

    override suspend fun retrieveAllMoments(): List<Moment> {
        return dao.retrieveAll().map(mapper::mapToDomain)
    }

    override suspend fun retrieveAllMomentsFlow(): Flow<List<Moment>> {
        return dao.retrieveAllFlow().map { it.map(mapper::mapToDomain) }
    }

}