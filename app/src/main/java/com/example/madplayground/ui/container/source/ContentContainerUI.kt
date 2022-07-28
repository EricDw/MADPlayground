package com.example.madplayground.ui.container.source

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.madplayground.ui.container.components.ContentContainerBottomNavBar
import com.example.madplayground.ui.container.components.ContentContainerNavigationRail
import com.example.madplayground.ui.container.components.ContentContainerTopAppBar
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.theme.source.ThemeController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentContainer.ContentContainer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
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

        ModalNavigationDrawer(
            drawerContent = drawerContent,
            gesturesEnabled = drawerGesturesEnabled,
            drawerState = drawerState,
        ) {

            Scaffold(
                modifier = modifier,
                topBar = {

                    ContentContainerTopAppBar()

                },
                bottomBar = {

                    ContentContainerBottomNavBar()

                },
                snackbarHost = snackbarHost,
                floatingActionButton = {

                    if (showFab) {
                        floatingActionButton()
                    }

                },
                floatingActionButtonPosition = fabPosition,
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

}