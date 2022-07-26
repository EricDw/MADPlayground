package com.example.madplayground.cache.moments.mapper

import com.example.madplayground.cache.moments.models.MomentEntity
import com.example.madplayground.data.moments.models.MomentData

interface MomentCacheMapper {

    fun mapToEntity(
        moment: MomentData
    ): MomentEntity

    fun mapToData(
        entity: MomentEntity
    ): MomentData

}