package com.example.madplayground.ui.timeline.source

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentContainer.Controller.TimelineScreenController(
    modifier: Modifier = Modifier,
    timelineScreenViewModel: TimelineScreen.ViewModel = hiltViewModel<AndroidTimelineScreenViewModel>(),
) {

    val scope = rememberCoroutineScope()

    val state = timelineScreenViewModel.state

    val windowConfiguration = LocalWindowConfiguration.current

    TimelineScreen(
        modifier = modifier.padding(top = 64.dp),
        state = state
    )

    LaunchedEffect(key1 = windowConfiguration) {

        titleId = R.string.title_timeline

        fabPosition = FabPosition.Center

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

        showNavigationRail = windowConfiguration.windowWidthType != WindowWidthType.COMPACT

        showBottomNavBar = !showNavigationRail

        showFab = !showNavigationRail

        railHeader = {

            FloatingActionButton(
                modifier = Modifier.padding(top = 64.dp),
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
                        if (drawerState.isClosed) {
                            drawerState.open()
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