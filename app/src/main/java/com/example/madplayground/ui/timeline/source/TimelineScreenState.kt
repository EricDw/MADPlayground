package com.example.madplayground.ui.timeline.source

import androidx.compose.runtime.*
import com.example.madplayground.ui.moments.models.MomentUiState
import com.example.madplayground.ui.timeline.models.TimelineScreen

class TimelineScreenState : TimelineScreen.State {

    override val moments: MutableList<MomentUiState> = mutableStateListOf()

    override var selectedMoment: MomentUiState? by mutableStateOf(null)

}

@Composable
fun rememberTimelineScreenState(
    initializer: TimelineScreenState.() -> Unit = {},
): TimelineScreenState = remember {
    TimelineScreenState().apply(initializer)
}