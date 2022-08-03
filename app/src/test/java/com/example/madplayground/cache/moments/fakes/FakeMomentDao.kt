package com.example.madplayground.cache.moments.fakes

import com.example.cache.moments.models.MomentDao
import com.example.cache.moments.models.MomentEntity
import kotlinx.coroutines.flow.Flow

class FakeMomentDao : MomentDao {

    override suspend fun insertEntity(entity: MomentEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun retrieveById(uid: String): MomentEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun retrieveAll(): List<MomentEntity> {
        TODO("Not yet implemented")
    }

    override fun retrieveAllFlow(): Flow<List<MomentEntity>> {
        TODO("Not yet implemented")
    }

}