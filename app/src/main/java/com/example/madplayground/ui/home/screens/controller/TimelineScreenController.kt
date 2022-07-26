package com.example.madplayground.ui.home.screens.controller

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.util.navigateToGraph
import com.example.madplayground.ui.home.screens.TimelineScreen
import com.example.madplayground.ui.home.source.AndroidTimelineScreenViewModel
import com.example.madplayground.ui.screens.MomentFormScreen
import com.example.madplayground.ui.screens.TimelineScreen

@Composable
fun TimelineScreenController(
    contentContainer: ContentContainer.Controller,
    modifier: Modifier = Modifier,
    timelineScreenViewModel: TimelineScreen.ViewModel = hiltViewModel<AndroidTimelineScreenViewModel>(),
) {

    val state = timelineScreenViewModel.state

    val containerEventHandler: (ContentContainer.Event) -> Unit = { event ->
        when (event) {

            ContentContainer.Event.FABClicked              -> {
                contentContainer.navHostController.navigate(
                    MomentFormScreen.ROUTE
                )
            }

            ContentContainer.Event.TimelineTabClicked      -> {
                /* no-op */
            }

            ContentContainer.Event.NavigationButtonClicked -> {
                TODO()
            }

            ContentContainer.Event.SettingsTabClicked      -> {
                contentContainer.navHostController.navigateToGraph(
                    ContentContainer.SETTINGS_GRAPH_ROUTE
                )
            }

        }

    }

    val screenInterface = remember {

        TimelineScreenImpl(containerEventHandler)

    }

    TimelineScreen(
        modifier = modifier,
        state = state
    )

    LaunchedEffect(key1 = screenInterface) {
        contentContainer.currentScreen = screenInterface
    }

}

private class TimelineScreenImpl(
    private val containerEventHandler: (ContentContainer.Event) -> Unit,
) : TimelineScreen {

    override fun onEvent(event: ContentContainer.Event) =
        containerEventHandler(event)

}