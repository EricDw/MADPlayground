package com.example.data.moments.mapper

import com.example.core.moments.source.MomentReadModel
import com.example.data.moments.models.MomentData
import com.example.data.moments.source.MomentDataImpl
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import java.util.*

class MomentDataMapperImpl : MomentDataMapper {

    override fun mapToDomain(momentData: MomentData): com.example.core.moments.models.Moment {

        return with(momentData) {

            MomentReadModel(
                id = com.example.core.moments.models.Moment.Id(value = UUID.fromString(id)),
                createdDateTime = LocalDateTime.parse(createdDateTime),
                description = description,
                date = date?.let(LocalDate::parse),
                time = time?.let(LocalTime::parse),
            )

        }

    }

    override fun mapToData(moment: com.example.core.moments.models.Moment): MomentData {

        return with(moment) {

            MomentDataImpl(
                id = id.value.toString(),
                createdDateTime = createdDateTime.toString(),
                description = description,
                date = date?.toString(),
                time = time?.toString(),
            )

        }

    }

}