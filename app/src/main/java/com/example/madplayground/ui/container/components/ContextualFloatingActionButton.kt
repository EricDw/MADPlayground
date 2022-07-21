package com.example.madplayground.ui.container.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.madplayground.R
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.util.ScreenContextPreviewParameterProvider
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun ContextualFloatingActionButton(
    screenContext: ContentContainer.ScreenContext,
    eventHandler: Message.Handler<ContentContainer.Event>,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
) {

    val iconography = LocalIconography.current

    var contentDescriptionId by remember {
        mutableStateOf<Int?>(null)
    }

    val icon by remember {
        mutableStateOf(iconography.editIcon)
    }

    if (isVisible) {

        FloatingActionButton(
            modifier = modifier,
            onClick = {
                eventHandler(
                    ContentContainer.Event.FABClicked
                )
            }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescriptionId?.let {
                    stringResource(id = it)
                }
            )
        }

    }

    LaunchedEffect(
        key1 = screenContext
    ) {

        when (screenContext) {

            ContentContainer.ScreenContext.HOME       -> {
                contentDescriptionId = R.string.description_add_moment
            }

            ContentContainer.ScreenContext.SETTINGS   -> {
                contentDescriptionId = R.string.description_add_moment
            }

            ContentContainer.ScreenContext.QUOTE_FORM -> {
                contentDescriptionId = R.string.description_add_moment
            }

        }

    }

}

@Preview
@Composable
private fun ContextualFloatingActionButtonPreview(
    @PreviewParameter(
        ScreenContextPreviewParameterProvider::class
    )
    screenContext: ContentContainer.ScreenContext
) {

    ContextualFloatingActionButton(
        screenContext = screenContext,
        eventHandler = { },
        modifier = Modifier.wrapContentSize()
    )

}