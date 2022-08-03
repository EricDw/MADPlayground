package com.example.madplayground.ui.moments.source

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.R
import com.example.madplayground.ui.config.LocalWindowConfiguration
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.moments.models.MomentFormScreen
import com.example.madplayground.ui.theme.models.LocalIconography

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

        titleId = R.string.title_compose_moment

        navigationIcon = {
            IconButton(
                onClick = {
                    screenEventHandler(
                        MomentFormScreen.Event.BackClicked
                    )
                }
            ) {
                Icon(
                    imageVector = LocalIconography.current.backIcon,
                    contentDescription = stringResource(
                        id = R.string.description_go_back)
                )
            }
        }

        showTopAppBar = true

        showFab = false

        showBottomNavBar = false

        showNavigationRail = false

    }

    MomentFormScreen(
        modifier = modifier.padding(top = 64.dp),
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
