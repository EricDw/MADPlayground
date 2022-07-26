package com.example.madplayground.ui.moments.screens.controller

import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.moments.screens.MomentFormScreen
import com.example.madplayground.ui.moments.source.AndroidMomentFormScreenViewModel
import com.example.madplayground.ui.screens.MomentFormScreen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MomentFormScreenController(
    container: ContentContainer.Controller,
    modifier: Modifier = Modifier,
    viewModel: MomentFormScreen.ViewModel = hiltViewModel<AndroidMomentFormScreenViewModel>(),
) {

    val scope = rememberCoroutineScope()

    val navController = container.navHostController

    val state = viewModel.state

    val scaffoldState = rememberBackdropScaffoldState(
        initialValue = BackdropValue.Concealed
    )

    var showDiscardChangesDialog by remember {
        mutableStateOf(false)
    }

    val submitted by state.submitted.collectAsState()

    val containerEventHandler: (ContentContainer.Event) -> Unit = { event: ContentContainer.Event ->

        when (event) {

            ContentContainer.Event.FABClicked              -> {
                TODO()
            }

            ContentContainer.Event.TimelineTabClicked -> {
                TODO()
            }

            ContentContainer.Event.NavigationButtonClicked -> {
                if (state.isEmpty) {
                    showDiscardChangesDialog = false
                    navController.popBackStack()
                } else {
                    showDiscardChangesDialog = true
                }
            }

            ContentContainer.Event.SettingsTabClicked      -> {
                TODO()
            }

        }

    }

    val screenEventHandler: (MomentFormScreen.Event) -> Unit = { event: MomentFormScreen.Event ->

        when (event) {

            is MomentFormScreen.Event.DateChanged            -> {
                viewModel.actionHandler(
                    MomentFormScreen.ViewModel.Command.ChangeDate(
                        newDate = event.newDate
                    )
                )
            }

            is MomentFormScreen.Event.TimeChanged            -> {
                viewModel.actionHandler(
                    MomentFormScreen.ViewModel.Command.ChangeTime(
                        newTime = event.newTime
                    )
                )
            }

            is MomentFormScreen.Event.ContentChanged         -> {
                viewModel.actionHandler(
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
                viewModel.actionHandler(
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

    LaunchedEffect(key1 = Unit) {

        container.currentScreen = MomentFormScreenImpl(
            scope = scope,
            scaffoldState = scaffoldState,
            containerEventHandler = containerEventHandler,
            screenEventHandler = screenEventHandler,
        )

    }

    MomentFormScreen(
        modifier = modifier,
        state = state,
        eventHandler = screenEventHandler,
        showDialog = showDiscardChangesDialog,
        scaffoldState = scaffoldState
    )

    LaunchedEffect(key1 = submitted) {
        if (submitted) {
            navController.popBackStack()
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
private class MomentFormScreenImpl(
    override val scope: CoroutineScope,
    override val scaffoldState: BackdropScaffoldState,
    private val containerEventHandler: (ContentContainer.Event) -> Unit,
    private val screenEventHandler: (MomentFormScreen.Event) -> Unit,
) : MomentFormScreen {

    override fun onEvent(event: ContentContainer.Event) =
        containerEventHandler(event)

    override fun onEvent(event: MomentFormScreen.Event) =
        screenEventHandler(event)

}