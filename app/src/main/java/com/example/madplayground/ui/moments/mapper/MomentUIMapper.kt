package com.example.madplayground.ui.moments.mapper

import com.example.madplayground.ui.moments.models.MomentUiState

interface MomentUIMapper {

    fun mapToUIState(moment: com.example.core.moments.models.Moment): MomentUiState

}