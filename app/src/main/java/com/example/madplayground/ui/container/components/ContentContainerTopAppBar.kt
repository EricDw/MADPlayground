package com.example.madplayground.ui.container.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.material3.Text
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.madplayground.ui.container.models.ContentContainer

@Composable
fun ContentContainer.ContentContainerTopAppBar(
    modifier: Modifier = Modifier,
) {

    val showNavIcon = !showNavigationRail

    val navIcon = if (showNavIcon) navigationIcon else null

    AnimatedVisibility(
        showTopAppBar,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = fadeOut()
    ) {

        SmallTopAppBar(
            modifier = modifier,
            navigationIcon = { navIcon?.invoke() },
            title = {
                titleId?.let { titleId ->
                    Text(text = stringResource(id = titleId))
                }
            },
            actions = topAppBarActions
        )

    }

}