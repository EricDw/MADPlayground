package com.example.madplayground.ui.container.components

import androidx.compose.runtime.Composable
import com.example.madplayground.ui.container.models.ContentContainer

@Composable
fun ContentContainer.ContentContainerFloatingActionButton() {
    if (showFab)
        floatingActionButton()
}