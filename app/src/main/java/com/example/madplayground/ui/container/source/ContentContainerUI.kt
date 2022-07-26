package com.example.madplayground.ui.container.source

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.madplayground.features.theme.ThemeController
import com.example.madplayground.ui.container.components.ContentContainerBottomNavBar
import com.example.madplayground.ui.container.components.ContentContainerFloatingActionButton
import com.example.madplayground.ui.container.components.ContentContainerNavigationRail
import com.example.madplayground.ui.container.components.ContentContainerTopAppBar
import com.example.madplayground.ui.container.models.ContentContainer

@Composable
fun ContentContainer(
    contentContainer: ContentContainer.Controller,
    modifier: Modifier = Modifier,
    content: @Composable (scaffoldPadding: PaddingValues) -> Unit,
) {

    val state = contentContainer.state

    val themeType by state.themeType.collectAsState()

    val iconographyType by state.iconographyType.collectAsState()

    val shapeType by state.shapeType.collectAsState()

    val navigationLabelVisibility by state.navigationLabelVisibility.collectAsState()

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
        themeType = themeType,
        iconographyType = iconographyType,
        shapeType = shapeType,
    ) {

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = modifier,
            topBar = {

                ContentContainerTopAppBar(
                    contentContainer = contentContainer
                )

            },
            bottomBar = {

                ContentContainerBottomNavBar(
                    contentContainer = contentContainer
                )

            },
            snackbarHost = { _ ->
                /* no-op */
            },
            floatingActionButton = {

                ContentContainerFloatingActionButton(
                    contentContainer = contentContainer,
                    isVisible = contentContainer.showBottomFAB
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
                    navigationLabelVisibility = navigationLabelVisibility
                )

                content(
                    scaffoldPadding
                )

            }

        }

    }

}