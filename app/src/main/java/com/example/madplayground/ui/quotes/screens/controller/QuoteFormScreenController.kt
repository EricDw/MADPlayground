package com.example.madplayground.ui.moments.screens.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.features.moments.ui.screens.form.MomentFormScreen
import com.example.madplayground.ui.moments.models.MomentFormScreen
import com.example.madplayground.ui.moments.source.AndroidMomentFormScreenViewModel

@Composable
fun MomentFormScreenController(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MomentFormScreen.ViewModel = hiltViewModel<AndroidMomentFormScreenViewModel>(),
) {

    val state by viewModel.state.collectAsState()

    val submitted by state.submitted.collectAsState()

    val eventHandler = Message.Handler<MomentFormScreen.Event> { theEvent ->

        when (theEvent) {

            is MomentFormScreen.Event.NavigationIconClicked -> {
                navController.popBackStack()
            }

            is MomentFormScreen.Event.AuthorChanged         -> {
                viewModel.actionHandler(
                    MomentFormScreen.ViewModel.Action.ChangeAuthor(
                        newAuthor = theEvent.newAuthor
                    )
                )
            }

            is MomentFormScreen.Event.ContentChanged        -> {
                viewModel.actionHandler(
                    MomentFormScreen.ViewModel.Action.ChangeContent(
                        newContent = theEvent.newContent
                    )
                )
            }

            is MomentFormScreen.Event.CancelClicked         -> {
                if (state.author.value.isEmpty() && state.content.value.isEmpty()) {
                    navController.popBackStack()
                } else {
                    TODO("Show Alert Dialog")
                }
            }

            is MomentFormScreen.Event.SaveClicked           -> {
                viewModel.actionHandler(
                    MomentFormScreen.ViewModel.Action.SubmitForm
                )
            }

        }

    }

    MomentFormScreen(
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