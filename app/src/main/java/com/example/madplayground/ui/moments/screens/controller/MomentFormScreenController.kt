package com.example.madplayground.ui.moments.screens.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.ui.moments.screens.MomentFormScreen
import com.example.madplayground.ui.moments.models.MomentFormScreen
import com.example.madplayground.ui.moments.models.MomentFormViewModel
import com.example.madplayground.ui.moments.source.AndroidMomentFormScreenMomentFormViewModel

@Composable
fun MomentFormScreenController(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MomentFormViewModel = hiltViewModel<AndroidMomentFormScreenMomentFormViewModel>(),
) {

    val state by viewModel.state.collectAsState()

    val submitted by state.submitted.collectAsState()

    val eventHandler = Message.Handler<MomentFormScreen.Event> { theEvent ->

        when (theEvent) {

            is MomentFormScreen.Event.DateChanged -> {
                viewModel.actionHandler(
                    MomentFormViewModel.Action.ChangeDate(
                        newDate = theEvent.newDate
                    )
                )
            }

            is MomentFormScreen.Event.TimeChanged -> {
                viewModel.actionHandler(
                    MomentFormViewModel.Action.ChangeTime(
                        newTime = theEvent.newTime
                    )
                )
            }

            is MomentFormScreen.Event.ContentChanged        -> {
                viewModel.actionHandler(
                    MomentFormViewModel.Action.ChangeContent(
                        newContent = theEvent.newContent
                    )
                )
            }

            is MomentFormScreen.Event.CancelClicked         -> {
                if (state.date.value.isEmpty() && state.description.value.isEmpty()) {
                    navController.popBackStack()
                } else {
                    TODO("Show Alert Dialog")
                }
            }

            is MomentFormScreen.Event.SaveClicked           -> {
                viewModel.actionHandler(
                    MomentFormViewModel.Action.SubmitForm
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