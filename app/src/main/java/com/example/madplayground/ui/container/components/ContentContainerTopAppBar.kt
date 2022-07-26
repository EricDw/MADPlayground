package com.example.madplayground.ui.container.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.madplayground.R
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.screens.MomentFormScreen
import com.example.madplayground.ui.screens.Screen
import com.example.madplayground.ui.screens.SettingsScreen
import com.example.madplayground.ui.screens.TimelineScreen
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun ContentContainerTopAppBar(
    contentContainer: ContentContainer,
    modifier: Modifier = Modifier,
) {

    val iconography = LocalIconography.current

    val screen = contentContainer.currentScreen

    val showNavIcon = !contentContainer.showNavigationRail

    var iconAndDescriptionId: Pair<ImageVector, Int>? by remember {
        mutableStateOf(null)
    }

    @StringRes
    var titleId: Int? by remember {
        mutableStateOf(null)
    }

    AnimatedVisibility(
        contentContainer.showTopAppBar,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = fadeOut()
    ) {

        TopAppBar(
            modifier = modifier,
            navigationIcon = iconAndDescriptionId?.let { (imageVector, descriptionId) ->

                if (showNavIcon) {
                    {
                        IconButton(
                            onClick = {
                                screen.onEvent(
                                    ContentContainer.Event.NavigationButtonClicked
                                )
                            }
                        ) {
                            Icon(
                                imageVector = imageVector,
                                contentDescription = stringResource(id = descriptionId)
                            )
                        }
                    }
                } else {
                    null
                }
            },
            title = {
                titleId?.let {
                    Text(text = stringResource(id = it))
                }
            }
        )

    }

    LaunchedEffect(key1 = screen) {

        when (screen) {

            Screen.EMPTY        -> {
                /* no-op */
            }

            is TimelineScreen   -> {
                iconAndDescriptionId = null
                titleId = R.string.title_timeline
            }

            is SettingsScreen   -> {

                iconAndDescriptionId = iconography.backIcon to R.string.description_go_back

                titleId = R.string.title_settings

            }

            is MomentFormScreen -> {

                /* no-op */

            }

        }

    }

}