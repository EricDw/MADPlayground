package com.example.data.moments.mapper

import com.example.core.moments.models.Moment
import com.example.data.moments.models.MomentData


interface MomentDataMapper {

    fun mapToDomain(momentData: MomentData): Moment

    fun mapToData(moment: Moment): MomentData

}