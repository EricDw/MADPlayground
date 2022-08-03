package com.example.madplayground.ui.settings.source

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madplayground.R
import com.example.madplayground.ui.config.CombinedWindowType
import com.example.madplayground.ui.config.LocalWindowConfiguration
import com.example.madplayground.ui.config.WindowWidthType
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.settings.models.SettingsScreen
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun ContentContainer.Controller.SettingsScreenController(
    modifier: Modifier = Modifier,
    settingsScreenViewModel: SettingsScreen.ViewModel = hiltViewModel<AndroidSettingsScreenViewModel>(),
) {

    val state = settingsScreenViewModel.state

    val windowConfiguration = LocalWindowConfiguration.current

    val settingsScreenEventHandler: (SettingsScreen.Event) -> Unit = { theEvent ->

        when (theEvent) {

            SettingsScreen.Event.ThemeTypeClicked       -> {

                settingsScreenViewModel.commandHandler(
                    SettingsScreen.ViewModel.Command.CycleThemeType
                )

            }

            SettingsScreen.Event.IconTypeClicked        -> {

                settingsScreenViewModel.commandHandler(
                    SettingsScreen.ViewModel.Command.CycleIconographyType
                )

            }

            SettingsScreen.Event.ShapeTypeClicked       -> {

                settingsScreenViewModel.commandHandler(
                    SettingsScreen.ViewModel.Command.CycleShapeType
                )

            }

            SettingsScreen.Event.LabelVisibilityClicked -> {

                settingsScreenViewModel.commandHandler(
                    SettingsScreen.ViewModel.Command.CycleLabelVisibility
                )

            }

            SettingsScreen.Event.BackClicked            -> {
                navHostController.popBackStack()
            }

        }

    }

    SettingsScreen(
        state = state,
        modifier = modifier,
        eventHandler = settingsScreenEventHandler,
    )

    LaunchedEffect(key1 = windowConfiguration) {

        titleId = R.string.title_settings

        navigationIcon = {
            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally(),
                exit = slideOutHorizontally()
            ) {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        imageVector = LocalIconography.current.backIcon,
                        contentDescription = stringResource(id = R.string.description_go_back)
                    )
                }
            }
        }

        showTopAppBar = windowConfiguration.combinedWindowType != CombinedWindowType.COMPACT_WIDTH_COMPACT_HEIGHT

        showNavigationRail = windowConfiguration.windowWidthType != WindowWidthType.COMPACT

        showBottomNavBar = windowConfiguration.windowWidthType == WindowWidthType.COMPACT

        showFab = false

        isTimelineSelected = false

        isSettingsSelected = true

        railHeader = {

            IconButton(
                modifier = Modifier.padding(top = 64.dp),
                onClick = { navHostController.popBackStack() }
            ) {
                Icon(
                    imageVector = LocalIconography.current.backIcon,
                    contentDescription = stringResource(
                        id = R.string.description_go_back
                    )
                )
            }

        }

    }

}