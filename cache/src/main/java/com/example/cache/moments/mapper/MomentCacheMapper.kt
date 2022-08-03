package com.example.cache.moments.mapper

import com.example.cache.moments.models.MomentEntity
import com.example.data.moments.models.MomentData

interface MomentCacheMapper {

    fun mapToEntity(
        moment: MomentData
    ): MomentEntity

    fun mapToData(
        entity: MomentEntity
    ): MomentData

}