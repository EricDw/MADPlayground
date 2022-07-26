package com.example.madplayground.ui.moments.mapper

import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.ui.moments.models.MomentUiState
import com.example.madplayground.ui.moments.source.MomentUiStateImpl

interface MomentUIMapper {

    fun mapToUIState(moment: Moment): MomentUiState

}