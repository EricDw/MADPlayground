package com.example.madplayground.data.moments.mapper

import com.example.madplayground.domain.moments.source.MomentReadModel
import com.example.madplayground.data.moments.models.MomentData
import com.example.madplayground.data.moments.source.MomentDataImpl
import com.example.madplayground.domain.moments.models.Moment
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import java.util.*

class MomentDataMapperImpl : MomentDataMapper {

    override fun mapToDomain(momentData: MomentData): Moment {

        return with(momentData) {

            MomentReadModel(
                id = Moment.Id(value = UUID.fromString(id)),
                createdDateTime = LocalDateTime.parse(createdDateTime),
                description = description,
                date = date?.let(LocalDate::parse),
                time = time?.let(LocalTime::parse),
            )

        }

    }

    override fun mapToData(moment: Moment): MomentData {

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