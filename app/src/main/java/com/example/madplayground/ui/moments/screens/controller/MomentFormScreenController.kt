package com.example.madplayground.ui.moments.screens.controller

import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.moments.models.MomentFormViewModel
import com.example.madplayground.ui.moments.screens.MomentFormScreen
import com.example.madplayground.ui.moments.source.AndroidMomentFormScreenMomentFormViewModel
import com.example.madplayground.ui.screen.MomentFormScreen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MomentFormScreenController(
    container: ContentContainer.Controller,
    modifier: Modifier = Modifier,
    viewModel: MomentFormViewModel = hiltViewModel<AndroidMomentFormScreenMomentFormViewModel>(),
) {

    val state by viewModel.state.collectAsState()

    val submitted by state.submitted.collectAsState()

    var showDiscardChangesDialog by remember {
        mutableStateOf(false)
    }

    val scaffoldState = rememberBackdropScaffoldState(
        initialValue = BackdropValue.Concealed
    )

    val scope = rememberCoroutineScope()

    val navController = container.navHostController

    val screen: MomentFormScreen = remember {

        object : MomentFormScreen {

            override val scope: CoroutineScope = scope

            override val scaffoldState: BackdropScaffoldState = scaffoldState

            override fun onEvent(event: ContentContainer.Event) {

                when (event) {

                    ContentContainer.Event.FABClicked              -> {
                        TODO()
                    }

                    ContentContainer.Event.HomeTabClicked          -> {
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

            override fun onEvent(
                event: MomentFormScreen.Event,
            ) {
                when (event) {

                    is MomentFormScreen.Event.DateChanged            -> {
                        viewModel.actionHandler(
                            MomentFormViewModel.Action.ChangeDate(
                                newDate = event.newDate
                            )
                        )
                    }

                    is MomentFormScreen.Event.TimeChanged            -> {
                        viewModel.actionHandler(
                            MomentFormViewModel.Action.ChangeTime(
                                newTime = event.newTime
                            )
                        )
                    }

                    is MomentFormScreen.Event.ContentChanged         -> {
                        viewModel.actionHandler(
                            MomentFormViewModel.Action.ChangeContent(
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
                            MomentFormViewModel.Action.SubmitForm
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

        }

    }

    MomentFormScreen(
        modifier = modifier,
        state = state,
        eventHandler = screen::onEvent,
        showDialog = showDiscardChangesDialog,
        scaffoldState = screen.scaffoldState
    )

    LaunchedEffect(key1 = submitted) {
        if (submitted) {
            navController.popBackStack()
        }
    }

    LaunchedEffect(key1 = screen) {
        container.setScreen(screen)
    }

}