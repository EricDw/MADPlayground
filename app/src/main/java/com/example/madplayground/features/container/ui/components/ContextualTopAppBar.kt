package com.example.madplayground.features.container.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.madplayground.R
import com.example.madplayground.features.container.api.ContentContainer
import com.example.madplayground.features.container.ui.util.ScreenContextPreviewParameterProvider
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.theme.models.LocalIconography

@Composable
fun ContextualTopAppBar(
    screenContext: ContentContainer.ScreenContext,
    eventHandler: Message.Handler<ContentContainer.Event>,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
) {

    var elevation by remember {
        mutableStateOf(1)
    }

    var backgroundColor: @Composable () -> Color by remember {
        mutableStateOf(
            { MaterialTheme.colors.primary }
        )
    }

    var navigationIcon: (@Composable () -> Unit)? by remember {
        mutableStateOf(null)
    }

    var titleId by remember {
        mutableStateOf(R.string.title_home)
    }

    if (isVisible) {

        TopAppBar(
            modifier = modifier,
            navigationIcon = navigationIcon,
            title = {
                Text(
                    text = stringResource(id = titleId),
                )
            },
            actions = { /* no-op */ },
            elevation = elevation.dp,
            backgroundColor = backgroundColor()
        )

    }

    LaunchedEffect(key1 = screenContext) {

        when (screenContext) {

            ContentContainer.ScreenContext.HOME       -> {
                elevation = 1
                backgroundColor = {
                    MaterialTheme.colors.primarySurface
                }
                navigationIcon = null /*{

                    NavigationIconButton(
                        imageVector = LocalIconography.current.menuIcon,
                        eventHandler = eventHandler,
                        descriptionId = R.string.description_menu
                    )

                }*/

                titleId = R.string.title_home

            }

            ContentContainer.ScreenContext.SETTINGS   -> {
                elevation = 1
                backgroundColor = {
                    MaterialTheme.colors.primarySurface
                }

                navigationIcon = {

                    NavigationIconButton(
                        imageVector = LocalIconography.current.backIcon,
                        eventHandler = eventHandler,
                        descriptionId = R.string.description_go_back
                    )

                }

                titleId = R.string.title_settings

            }

            ContentContainer.ScreenContext.QUOTE_FORM -> {
                elevation = 0
                backgroundColor = {
                    MaterialTheme.colors.primary
                }

                navigationIcon = {

                    NavigationIconButton(
                        imageVector = LocalIconography.current.backIcon,
                        eventHandler = eventHandler,
                        descriptionId = R.string.description_go_back
                    )

                }

                titleId = R.string.title_compose_quote

            }

        }

    }

}

@Composable
private fun NavigationIconButton(
    imageVector: ImageVector,
    @StringRes
    descriptionId: Int,
    eventHandler: Message.Handler<ContentContainer.Event>,
) {

    IconButton(
        onClick = {

            eventHandler(
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

@Preview
@Composable
private fun ContextualTopAppBarPreview(
    @PreviewParameter(
        ScreenContextPreviewParameterProvider::class
    )
    screenContext: ContentContainer.ScreenContext,
) {

    ContextualTopAppBar(
        screenContext = screenContext,
        eventHandler = { /* no-op */ }
    )

}
