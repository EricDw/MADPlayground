package com.example.madplayground.features.container.ui.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.madplayground.features.container.api.ContentContainer

class ScreenContextPreviewParameterProvider:
    PreviewParameterProvider<ContentContainer.ScreenContext> {
    override val values: Sequence<ContentContainer.ScreenContext> =
        ContentContainer.ScreenContext.values().asSequence()
}