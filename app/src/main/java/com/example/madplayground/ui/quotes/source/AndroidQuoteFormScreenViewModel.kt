package com.example.madplayground.ui.quotes.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.app.models.App
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.ui.quotes.models.QuoteFormScreen
import com.example.madplayground.ui.quotes.models.QuoteFormScreen.*
import com.example.madplayground.ui.quotes.models.QuoteFormScreen.ViewModel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AndroidQuoteFormScreenViewModel @Inject constructor(
    app: App,
) : ViewModel(), QuoteFormScreen.ViewModel {

    private val delegate = QuoteFormScreenViewModel(
        app = app,
        scope = viewModelScope,
    )

    override val state: StateFlow<State> =
        delegate.state

    override val actionHandler: Message.Handler<Action> =
        delegate.actionHandler

}