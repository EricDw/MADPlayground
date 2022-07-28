package com.example.madplayground.ui.timeline.source

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.R
import com.example.madplayground.ui.config.CombinedWindowType
import com.example.madplayground.ui.config.LocalWindowConfiguration
import com.example.madplayground.ui.config.WindowWidthType
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.moments.models.MomentFormScreen
import com.example.madplayground.ui.theme.models.LocalIconography
import com.example.madplayground.ui.timeline.models.TimelineScreen
import kotlinx.coroutines.launch

@Composable
fun ContentContainer.Controller.TimelineScreenController(
    modifier: Modifier = Modifier,
    timelineScreenViewModel: TimelineScreen.ViewModel = hiltViewModel<AndroidTimelineScreenViewModel>(),
) {

    val scope = rememberCoroutineScope()

    val state = timelineScreenViewModel.state

    val windowConfiguration = LocalWindowConfiguration.current

    TimelineScreen(
        modifier = modifier,
        state = state
    )

    LaunchedEffect(key1 = windowConfiguration) {

        titleId = R.string.title_timeline

        fabPosition = FabPosition.Center

        dockFab = true

        floatingActionButton = {

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

        showFab = windowConfiguration.windowWidthType == WindowWidthType.COMPACT

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

        if (windowConfiguration.combinedWindowType == CombinedWindowType.COMPACT_WIDTH_COMPACT_HEIGHT) {

            showTopAppBar = true

            drawerGesturesEnabled = true

            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        if (containerScaffoldState.drawerState.isClosed) {
                            containerScaffoldState.drawerState.open()
                        }
                    }
                }) {
                    Icon(
                        imageVector = LocalIconography.current.menuIcon,
                        contentDescription = stringResource(
                            id = R.string.description_open_menu)
                    )
                }
            }

        } else {

            navigationIcon = null

            showTopAppBar = true

            isTimelineSelected = true

            isSettingsSelected = false

        }

    }

}