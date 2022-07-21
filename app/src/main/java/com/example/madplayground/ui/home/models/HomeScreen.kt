package com.example.madplayground.ui.home.models

import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.ui.quotes.models.MomentUiState
import kotlinx.coroutines.flow.StateFlow

interface HomeScreen {

    companion object {

        const val ROUTE: String = "home_screen"

    }

    sealed interface Event {

    }

    interface State {

        val moments: List<MomentUiState>

        val momentOfTheDay: MomentUiState?

    }

    interface ViewModel {

        val state: StateFlow<State>

        sealed interface Action : Message {

        }

    }

}