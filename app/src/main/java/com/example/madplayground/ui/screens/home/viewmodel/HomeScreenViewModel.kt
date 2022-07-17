package com.example.madplayground.ui.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.madplayground.features.app.apis.App
import com.example.madplayground.ui.screens.home.HomeScreenState
import com.example.madplayground.ui.screens.home.api.HomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val app: App,
) : ViewModel(), HomeScreen.ViewModel {

    private val tag = this::class.simpleName

    private val logs
        get() = app.logs

    private val screenState = HomeScreenState()

    private val _state = MutableStateFlow(screenState)

    override val state: StateFlow<HomeScreen.State> =
        _state.asStateFlow()

    override fun handle(event: HomeScreen.Event) {

        when (event) {

            HomeScreen.Event.HomeScreenStarted -> {
                logs.logError(
                    tag = tag,
                    message = "Event Handling Not Implemented"
                )
            }

        }

    }

}