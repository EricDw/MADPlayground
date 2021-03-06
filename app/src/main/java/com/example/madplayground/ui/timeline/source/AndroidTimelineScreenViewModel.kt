package com.example.madplayground.ui.timeline.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.moments.usecases.RetrieveAllMomentUseCase
import com.example.madplayground.ui.moments.mapper.MomentUIMapper
import com.example.madplayground.ui.timeline.models.TimelineScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidTimelineScreenViewModel @Inject constructor(
    logs: com.example.common.logs.models.Logs,
    momentUIMapper: MomentUIMapper,
    retrieveAllMomentUseCase: RetrieveAllMomentUseCase,
) : ViewModel(), TimelineScreen.ViewModel {

    private val delegate = TimelineScreenViewModel(
        logs = logs,
        momentUIMapper = momentUIMapper,
        retrieveAllMomentUseCase = retrieveAllMomentUseCase,
        scope = viewModelScope
    )

    override val state: TimelineScreen.State =
        delegate.state

}