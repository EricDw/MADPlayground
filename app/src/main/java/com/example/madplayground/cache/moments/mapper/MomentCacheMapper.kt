package com.example.madplayground.cache.moments.mapper

import com.example.madplayground.cache.moments.models.MomentEntity
import com.example.madplayground.domain.moments.models.Moment

interface MomentCacheMapper {

    fun mapToEntity(
        moment: Moment
    ): MomentEntity

    fun mapToDomain(
        entity: MomentEntity
    ): Moment

}