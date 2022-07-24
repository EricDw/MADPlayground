package com.example.madplayground.ui.container.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.madplayground.features.theme.ThemeController
import com.example.madplayground.ui.container.components.ContentContainerBottomNavBar
import com.example.madplayground.ui.container.components.ContentContainerFloatingActionButton
import com.example.madplayground.ui.container.components.ContentContainerNavigationRail
import com.example.madplayground.ui.container.components.ContentContainerTopAppBar
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.source.rememberContentContainerState

@Composable
fun ContentContainer(
    contentContainer: ContentContainer,
    modifier: Modifier = Modifier,
    state: ContentContainer.State = rememberContentContainerState(),
    showTopAppBar: Boolean = true,
    showBottomNavBar: Boolean = true,
    showNavigationRail: Boolean = false,
    showBottomFAB: Boolean = true,
    content: @Composable (scaffoldPadding: PaddingValues) -> Unit,
) {

    val isDrawerUnlocked = false

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    ) { drawerValue ->

        when (drawerValue) {

            DrawerValue.Closed -> {
                true
            }

            DrawerValue.Open   -> {

                isDrawerUnlocked

            }

        }

    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scaffoldState: ScaffoldState =
        rememberScaffoldState(
            drawerState = drawerState,
            snackbarHostState = snackbarHostState
        )

    ThemeController(
        themeType = state.themeType,
        iconographyType = state.iconographyType,
        shapeType = state.shapeType,
    ) {

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = modifier,
            topBar = {

                ContentContainerTopAppBar(
                    contentContainer = contentContainer,
                    isVisible = showTopAppBar
                )

            },
            bottomBar = {

                ContentContainerBottomNavBar(
                    contentContainer = contentContainer,
                    isVisible = showBottomNavBar
                )

            },
            snackbarHost = { _ ->
                /* no-op */
            },
            floatingActionButton = {

                ContentContainerFloatingActionButton(
                    contentContainer = contentContainer,
                    isVisible = showBottomFAB
                )

            },
            drawerContent = null,
            drawerGesturesEnabled = isDrawerUnlocked,
        ) { scaffoldPadding ->

            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                ContentContainerNavigationRail(
                    contentContainer = contentContainer,
                    isVisible = showNavigationRail,
                    navigationLabelVisibility = state.navigationLabelVisibility
                )

                content(
                    scaffoldPadding
                )

            }

        }

    }

}