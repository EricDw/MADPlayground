package com.example.madplayground.ui.timeline.screens.controller

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.R
import com.example.madplayground.ui.config.LocalWindowConfiguration
import com.example.madplayground.ui.config.WindowWidthType
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.timeline.screens.TimelineScreen
import com.example.madplayground.ui.timeline.source.AndroidTimelineScreenViewModel
import com.example.madplayground.ui.moments.models.MomentFormScreen
import com.example.madplayground.ui.timeline.models.TimelineScreen
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun ContentContainer.Controller.TimelineScreenController(
    modifier: Modifier = Modifier,
    timelineScreenViewModel: TimelineScreen.ViewModel = hiltViewModel<AndroidTimelineScreenViewModel>(),
) {

    val state = timelineScreenViewModel.state

    val windowConfiguration = LocalWindowConfiguration.current

    TimelineScreen(
        modifier = modifier,
        state = state
    )

    LaunchedEffect(key1 = windowConfiguration) {

        titleId = R.string.title_timeline

        topAppBarIcon = null

        showTopAppBar = true

        isTimelineSelected = true

        isSettingsSelected = false

        bottomFAB = {

            FloatingActionButton(
                onClick = {
                    navHostController.navigate(MomentFormScreen.ROUTE)
                }
            ) {

                Icon(
                    imageVector = LocalIconography.current.editIcon,
                    contentDescription = stringResource(
                        id = R.string.description_add_moment)
                )

            }

        }

        showBottomFAB = windowConfiguration.windowWidthType == WindowWidthType.COMPACT

        showNavigationRail = windowConfiguration.windowWidthType != WindowWidthType.COMPACT

        showBottomNavBar = windowConfiguration.windowWidthType == WindowWidthType.COMPACT

        railHeader = {

            FloatingActionButton(
                onClick = { navHostController.navigate(MomentFormScreen.ROUTE) }
            ) {
                Icon(
                    imageVector = LocalIconography.current.editIcon,
                    contentDescription = stringResource(
                        id = R.string.description_add_moment)
                )
            }

        }

    }

}