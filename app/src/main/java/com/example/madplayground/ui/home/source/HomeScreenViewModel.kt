package com.example.madplayground.ui.home.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.app.models.App
import com.example.madplayground.domain.quotes.models.Quote
import com.example.madplayground.ui.quotes.components.QuoteState
import com.example.madplayground.ui.home.models.HomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val app: App,
) : ViewModel(), HomeScreen.ViewModel, App by app {

    private val tag = this::class.simpleName

    private val screenState = HomeScreenState()

    private val _state = MutableStateFlow(screenState)

    override val state: StateFlow<HomeScreen.State> =
        _state.asStateFlow()

    init {
        quotes.quoteOfTheDay.onEach { quote ->
            screenState.quoteOfTheDay = quote?.toState()
        }.launchIn(viewModelScope)
    }

    private fun Quote.toState(): Quote.State {
        return QuoteState(
            id = id,
            content = content
        )
    }

}