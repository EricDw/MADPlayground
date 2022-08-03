package com.example.cache.moments.source

import com.example.cache.moments.mapper.MomentCacheMapper
import com.example.cache.moments.models.MomentEntity

class MomentCacheMapperImpl : MomentCacheMapper {

    override fun mapToEntity(
        moment: com.example.data.moments.models.MomentData,
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
    ): com.example.data.moments.models.MomentData {
        return with(entity) {
            com.example.data.moments.source.MomentDataImpl(
                id = uid,
                createdDateTime = created_date_time,
                description = description,
                date = date,
                time = time,
            )
        }
    }

}