package com.example.madplayground.ui.timeline.models

import com.example.madplayground.ui.moments.models.MomentUiState

interface TimelineScreen {

    interface State {

        val moments: List<MomentUiState>

        val selectedMoment: MomentUiState?

    }

    interface ViewModel {

        val state: State

        sealed interface Command {

        }

    }

    companion object {

        const val ROUTE: String = "timeline_screen"

    }

}