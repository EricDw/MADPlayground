package com.example.cache.moments.source

import com.example.cache.moments.mapper.MomentCacheMapper
import com.example.cache.moments.models.MomentDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalMomentDataSourceImpl(
    private val dao: MomentDao,
    private val mapper: MomentCacheMapper,
) : com.example.data.moments.models.LocalMomentDataSource {

    override suspend fun addMomentData(
        moment: com.example.data.moments.models.MomentData,
    ) {

        val entity = mapper.mapToEntity(
            moment
        )

        dao.insertEntity(entity = entity)
    }

    override suspend fun retrieveMomentDataById(
        id: String,
    ): com.example.data.moments.models.MomentData? {
        return dao.retrieveById(uid = id)?.let(mapper::mapToData)
    }

    override suspend fun retrieveAllMomentData(): List<com.example.data.moments.models.MomentData> {
        return dao.retrieveAll().map(mapper::mapToData)
    }

    override suspend fun retrieveAllMomentDataFlow(): Flow<List<com.example.data.moments.models.MomentData>> {
        return dao.retrieveAllFlow().map { it.map(mapper::mapToData) }
    }

}