package com.example.madplayground.ui.moments.source

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.ui.config.LocalWindowConfiguration
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.moments.models.MomentFormScreen

@Composable
fun ContentContainer.Controller.MomentFormScreenController(
    modifier: Modifier = Modifier,
    viewModel: MomentFormScreen.ViewModel = hiltViewModel<AndroidMomentFormScreenViewModel>(),
) {

    val windowConfiguration = LocalWindowConfiguration.current

    val navController = navHostController

    val state = viewModel.state

    var showDiscardChangesDialog by remember {
        mutableStateOf(false)
    }

    val submitted by state.submitted.collectAsState()

    val screenEventHandler: (MomentFormScreen.Event) -> Unit = { event: MomentFormScreen.Event ->

        when (event) {

            is MomentFormScreen.Event.DateChanged            -> {
                viewModel.commandHandler(
                    MomentFormScreen.ViewModel.Command.ChangeDate(
                        newDate = event.newDate
                    )
                )
            }

            is MomentFormScreen.Event.TimeChanged            -> {
                viewModel.commandHandler(
                    MomentFormScreen.ViewModel.Command.ChangeTime(
                        newTime = event.newTime
                    )
                )
            }

            is MomentFormScreen.Event.ContentChanged         -> {
                viewModel.commandHandler(
                    MomentFormScreen.ViewModel.Command.ChangeContent(
                        newContent = event.newContent
                    )
                )
            }

            is MomentFormScreen.Event.CancelClicked          -> {
                if (state.isEmpty) {
                    navController.popBackStack()
                } else {
                    showDiscardChangesDialog = true
                }
            }

            is MomentFormScreen.Event.SaveClicked            -> {
                viewModel.commandHandler(
                    MomentFormScreen.ViewModel.Command.SubmitForm
                )
            }

            is MomentFormScreen.Event.BackClicked            -> {
                if (state.isEmpty) {
                    showDiscardChangesDialog = false
                    navController.popBackStack()
                } else {
                    showDiscardChangesDialog = true
                }
            }

            is MomentFormScreen.Event.DiscardChangesClicked  -> {
                showDiscardChangesDialog = false
                navController.popBackStack()
            }

            is MomentFormScreen.Event.DismissDialogRequested -> {
                showDiscardChangesDialog = false
            }

        }

    }

    LaunchedEffect(key1 = windowConfiguration) {

        showTopAppBar = false

        showFab = false

        showBottomNavBar = false

        showNavigationRail = false

    }

    MomentFormScreen(
        modifier = modifier,
        state = state,
        eventHandler = screenEventHandler,
        showDialog = showDiscardChangesDialog,
    )

    LaunchedEffect(key1 = submitted) {
        if (submitted) {
            navController.popBackStack()
        }
    }

}
