package com.example.madplayground.ui.container.source

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.madplayground.features.theme.ThemeController
import com.example.madplayground.ui.container.components.ContentContainerBottomNavBar
import com.example.madplayground.ui.container.components.ContentContainerFloatingActionButton
import com.example.madplayground.ui.container.components.ContentContainerNavigationRail
import com.example.madplayground.ui.container.components.ContentContainerTopAppBar
import com.example.madplayground.ui.container.models.ContentContainer

@Composable
fun ContentContainer.Controller.ContentContainer(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    content: @Composable (scaffoldPadding: PaddingValues) -> Unit,
) {

    val state = state

    val themeType by state.themeType.collectAsState()

    val iconographyType by state.iconographyType.collectAsState()

    val shapeType by state.shapeType.collectAsState()

    val navigationLabelVisibility by state.navigationLabelVisibility.collectAsState()

    ThemeController(
        themeType = themeType,
        iconographyType = iconographyType,
        shapeType = shapeType,
    ) {

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = modifier,
            topBar = {

                ContentContainerTopAppBar()

            },
            bottomBar = {

                ContentContainerBottomNavBar()

            },
            snackbarHost = snackbarHost,
            floatingActionButton = {

                ContentContainerFloatingActionButton()

            },
            drawerContent = drawerContent,
            drawerGesturesEnabled = drawerGesturesEnabled,
            floatingActionButtonPosition = fabPosition,
            isFloatingActionButtonDocked = dockFab
        ) { scaffoldPadding ->

            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                ContentContainerNavigationRail(
                    navigationLabelVisibility = navigationLabelVisibility
                )

                content(
                    scaffoldPadding
                )

            }

        }

    }

}