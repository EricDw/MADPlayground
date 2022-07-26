package com.example.madplayground.cache.moments.source

import com.example.madplayground.cache.moments.mapper.MomentCacheMapper
import com.example.madplayground.cache.moments.models.MomentDao
import com.example.madplayground.data.moments.models.MomentData
import com.example.madplayground.data.moments.models.LocalMomentDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalMomentDataSourceImpl(
    private val dao: MomentDao,
    private val mapper: MomentCacheMapper,
) : LocalMomentDataSource {

    override suspend fun addMomentData(
        moment: MomentData,
    ) {

        val entity = mapper.mapToEntity(
            moment
        )

        dao.insertEntity(entity = entity)
    }

    override suspend fun retrieveMomentDataById(
        id: String,
    ): MomentData? {
        return dao.retrieveById(uid = id)?.let(mapper::mapToData)
    }

    override suspend fun retrieveAllMomentData(): List<MomentData> {
        return dao.retrieveAll().map(mapper::mapToData)
    }

    override suspend fun retrieveAllMomentDataFlow(): Flow<List<MomentData>> {
        return dao.retrieveAllFlow().map { it.map(mapper::mapToData) }
    }

}