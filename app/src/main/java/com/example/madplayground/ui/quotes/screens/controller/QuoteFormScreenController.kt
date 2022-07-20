package com.example.madplayground.ui.quotes.screens.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.features.quotes.ui.screens.form.QuoteFormScreen
import com.example.madplayground.ui.quotes.models.QuoteFormScreen
import com.example.madplayground.ui.quotes.source.AndroidQuoteFormScreenViewModel

@Composable
fun QuoteFormScreenController(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: QuoteFormScreen.ViewModel = hiltViewModel<AndroidQuoteFormScreenViewModel>(),
) {

    val state by viewModel.state.collectAsState()

    val submitted by state.submitted.collectAsState()

    val eventHandler = Message.Handler<QuoteFormScreen.Event> { theEvent ->

        when (theEvent) {

            is QuoteFormScreen.Event.NavigationIconClicked -> {
                navController.popBackStack()
            }

            is QuoteFormScreen.Event.AuthorChanged         -> {
                viewModel.actionHandler(
                    QuoteFormScreen.ViewModel.Action.ChangeAuthor(
                        newAuthor = theEvent.newAuthor
                    )
                )
            }

            is QuoteFormScreen.Event.ContentChanged        -> {
                viewModel.actionHandler(
                    QuoteFormScreen.ViewModel.Action.ChangeContent(
                        newContent = theEvent.newContent
                    )
                )
            }

            is QuoteFormScreen.Event.CancelClicked         -> {
                if (state.author.value.isEmpty() && state.content.value.isEmpty()) {
                    navController.popBackStack()
                } else {
                    TODO("Show Alert Dialog")
                }
            }

            is QuoteFormScreen.Event.SaveClicked           -> {
                viewModel.actionHandler(
                    QuoteFormScreen.ViewModel.Action.SubmitForm
                )
            }

        }

    }

    QuoteFormScreen(
        modifier = modifier,
        state = state,
        eventHandler = eventHandler
    )

    LaunchedEffect(key1 = submitted) {
        if (submitted) {
            navController.popBackStack()
        }
    }

}