package com.example.madplayground.ui.screen

import com.example.madplayground.domain.messages.Message
import com.example.madplayground.ui.moments.models.MomentUiState
import kotlinx.coroutines.flow.StateFlow

interface HomeScreen : Screen {

    interface State {

        val moments: List<MomentUiState>

        val momentOfTheDay: MomentUiState?

    }

    interface ViewModel {

        val state: StateFlow<State>

        sealed interface Action : Message {

        }

    }

    companion object {

        const val ROUTE: String = "home_screen"

    }

}