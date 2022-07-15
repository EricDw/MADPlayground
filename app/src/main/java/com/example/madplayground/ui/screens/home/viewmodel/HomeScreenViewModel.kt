package com.example.madplayground.ui.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.madplayground.logs.api.Logger
import com.example.madplayground.ui.screens.home.HomeScreenState
import com.example.madplayground.ui.screens.home.api.HomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val logger: Logger,
) : ViewModel(), HomeScreen.ViewModel {

    private val tag = this::class.simpleName

    private val _state = MutableStateFlow(HomeScreenState())

    override val state: StateFlow<HomeScreen.State> =
        _state.asStateFlow()

    override fun handle(event: HomeScreen.Event) {

        when (event) {

            HomeScreen.Event.HomeScreenStarted -> {
                logger.logError(
                    tag = tag,
                    message = "Event Handling Not Implemented"
                )
            }

        }

    }

}