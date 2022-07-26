package com.example.madplayground.data.moments.mapper

import com.example.madplayground.data.moments.models.MomentData
import com.example.madplayground.domain.moments.models.Moment

interface MomentDataMapper {

    fun mapToDomain(momentData: MomentData): Moment

    fun mapToData(moment: Moment): MomentData

}