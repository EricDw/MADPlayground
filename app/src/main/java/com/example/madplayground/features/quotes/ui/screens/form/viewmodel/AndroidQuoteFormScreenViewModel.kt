package com.example.madplayground.features.quotes.ui.screens.form.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.features.app.apis.App
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.quotes.ui.screens.form.api.QuoteFormScreen
import com.example.madplayground.features.quotes.ui.screens.form.api.QuoteFormScreen.*
import com.example.madplayground.features.quotes.ui.screens.form.api.QuoteFormScreen.ViewModel.*
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