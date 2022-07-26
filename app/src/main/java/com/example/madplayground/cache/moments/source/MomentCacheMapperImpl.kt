package com.example.madplayground.cache.moments.source

import com.example.madplayground.cache.moments.mapper.MomentCacheMapper
import com.example.madplayground.cache.moments.models.MomentEntity
import com.example.madplayground.data.moments.models.MomentData
import com.example.madplayground.data.moments.source.MomentDataImpl

class MomentCacheMapperImpl : MomentCacheMapper {

    override fun mapToEntity(
        moment: MomentData,
    ): MomentEntity {

        return with(moment) {
            MomentEntity(
                uid = id,
                created_date_time = createdDateTime,
                description = description,
                date = date,
                time = time,
            )
        }

    }

    override fun mapToData(
        entity: MomentEntity,
    ): MomentData {
        return with(entity) {
            MomentDataImpl(
                id = uid,
                createdDateTime = created_date_time,
                description = description,
                date = date,
                time = time,
            )
        }
    }

}