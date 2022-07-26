package com.example.madplayground.ui.moments.mapper

import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.ui.moments.models.MomentUiState

interface MomentUIMapper {

    fun mapToUIState(moment: Moment): MomentUiState

}