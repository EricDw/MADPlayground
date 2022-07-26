package com.example.madplayground.ui.moments.source

import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.ui.moments.mapper.MomentUIMapper
import com.example.madplayground.ui.moments.models.MomentUiState

class MomentUIMapperImpl : MomentUIMapper {

    override fun mapToUIState(
        moment: Moment
    ): MomentUiState {

        return with(moment) {
            MomentUiStateImpl(
                id = id.value.toString(),
                description = description,
                date = date?.toString(),
                time = time?.toString(),
            )
        }

    }

}