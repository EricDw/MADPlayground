package com.example.madplayground.ui.home.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.app.models.App
import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.domain.moments.usecases.RetrieveMomentUseCase
import com.example.madplayground.ui.home.models.HomeScreen
import com.example.madplayground.ui.quotes.models.MomentUiState
import com.example.madplayground.ui.quotes.source.MomentUiStateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val app: App,
    private val retrieveMomentUseCase: RetrieveMomentUseCase,
) : ViewModel(), HomeScreen.ViewModel, App by app {

    private val tag = this::class.simpleName

    private val screenState = HomeScreenState()

    private val _state = MutableStateFlow(screenState)

    override val state: StateFlow<HomeScreen.State> =
        _state.asStateFlow()

    init {
        retrieveMomentUseCase().onEach { result ->

            when (result) {

                is RetrieveMomentUseCase.Result.Complete -> {
                    screenState.momentOfTheDay = result.moment?.toState()
                }

                RetrieveMomentUseCase.Result.Error       -> {
                    logs.logDebug(
                        tag = tag,
                        message = "Error Retrieving Moment",
                    )
                }

                RetrieveMomentUseCase.Result.Running     -> {
                    logs.logDebug(
                        tag = tag,
                        message = "Retrieving Moment",
                    )
                }

            }

        }.launchIn(viewModelScope)
    }

    private fun Moment.toState(): MomentUiState {
        return MomentUiStateImpl(
            id = id,
            description = description
        )
    }

}