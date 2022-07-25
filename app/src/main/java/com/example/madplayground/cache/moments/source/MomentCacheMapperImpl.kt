package com.example.madplayground.cache.moments.source

import com.example.madplayground.cache.moments.mapper.MomentCacheMapper
import com.example.madplayground.cache.moments.models.MomentEntity
import com.example.madplayground.domain.moments.models.Moment
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import java.util.UUID

class MomentCacheMapperImpl : MomentCacheMapper {

    override fun mapToEntity(
        moment: Moment,
    ): MomentEntity {

        return with(moment) {
            MomentEntity(
                uid = id.value.toString(),
                created_date_time = createdDateTime.toString(),
                description = description,
                date = date?.toString(),
                time = time?.toString(),
            )
        }

    }

    override fun mapToDomain(
        entity: MomentEntity,
    ): Moment {
        return with(entity) {
            MomentReadModel(
                id = Moment.Id(value = UUID.fromString(uid)),
                createdDateTime = LocalDateTime.parse(created_date_time),
                description = description,
                date = date?.let(LocalDate::parse),
                time = time?.let(LocalTime::parse),
            )
        }
    }

}