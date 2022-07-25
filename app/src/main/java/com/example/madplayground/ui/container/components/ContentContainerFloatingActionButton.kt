package com.example.madplayground.ui.container.components

import androidx.annotation.StringRes
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.madplayground.R
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.screen.HomeScreen
import com.example.madplayground.ui.screen.MomentFormScreen
import com.example.madplayground.ui.screen.Screen
import com.example.madplayground.ui.screen.SettingsScreen
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun ContentContainerFloatingActionButton(
    contentContainer: ContentContainer,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
) {

    val iconography = LocalIconography.current

    val screen = contentContainer.currentScreen

    var icon by remember {
        mutableStateOf(iconography.editIcon)
    }

    @StringRes
    var descriptionId by remember {
        mutableStateOf(R.string.description_add_moment)
    }

    if (isVisible) {

        FloatingActionButton(
            onClick = {
                screen.onEvent(
                    ContentContainer.Event.FABClicked
                )
            },
            modifier = modifier,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = descriptionId)
            )
        }

    }

    LaunchedEffect(key1 = screen) {

        when (screen) {

            is Screen.EMPTY     -> {
                /* no-op */
            }

            is HomeScreen       -> {

                icon = iconography.editIcon
                descriptionId = R.string.description_add_moment

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