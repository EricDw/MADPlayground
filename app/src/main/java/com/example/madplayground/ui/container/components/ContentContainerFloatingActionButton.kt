package com.example.madplayground.ui.container.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.home.components.HomeScreenFAB
import com.example.madplayground.ui.screen.HomeScreen
import com.example.madplayground.ui.screen.MomentFormScreen
import com.example.madplayground.ui.screen.Screen
import com.example.madplayground.ui.screen.SettingsScreen

@Composable
fun ContentContainerFloatingActionButton(
    contentContainer: ContentContainer,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
) {

    val screen = contentContainer.currentScreen

    if (isVisible) {

        when (screen) {

            is Screen.EMPTY     -> {
                /* no-op */
            }

            is HomeScreen       -> {

                HomeScreenFAB(
                    screen = screen,
                    modifier = modifier
                )

            }

            is MomentFormScreen -> {
                /* no-op */
            }

            is SettingsScreen   -> {
                /* no-op */
            }

        }

    }

}