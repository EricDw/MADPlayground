package com.example.madplayground.ui.container.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.home.components.HomeScreenTopAppBar
import com.example.madplayground.ui.moments.components.MomentFormTopAppBar
import com.example.madplayground.ui.screen.HomeScreen
import com.example.madplayground.ui.screen.MomentFormScreen
import com.example.madplayground.ui.screen.Screen
import com.example.madplayground.ui.screen.SettingsScreen
import com.example.madplayground.ui.settings.components.SettingsScreenTopAppBar

@Composable
fun ContentContainerTopAppBar(
    contentContainer: ContentContainer,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
) {

    val screen = contentContainer.currentScreen

    if (isVisible) {

        when (screen) {

            Screen.EMPTY        -> {
                /* no-op */
            }

            is HomeScreen       -> {
                HomeScreenTopAppBar(screen = screen)
            }

            is MomentFormScreen -> {

                /* no-op */

            }

            is SettingsScreen   -> {

                SettingsScreenTopAppBar(
                    screen = screen,
                    modifier = modifier
                )

            }

        }

    }

}