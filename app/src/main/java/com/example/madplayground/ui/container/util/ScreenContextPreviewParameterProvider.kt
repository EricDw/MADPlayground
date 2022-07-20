package com.example.madplayground.ui.container.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.madplayground.ui.container.models.ContentContainer

class ScreenContextPreviewParameterProvider:
    PreviewParameterProvider<ContentContainer.ScreenContext> {
    override val values: Sequence<ContentContainer.ScreenContext> =
        ContentContainer.ScreenContext.values().asSequence()
}