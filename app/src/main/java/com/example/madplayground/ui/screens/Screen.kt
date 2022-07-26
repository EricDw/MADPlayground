package com.example.madplayground.ui.screens

import com.example.madplayground.ui.container.models.ContentContainer

sealed interface Screen {

    object EMPTY : Screen {
        override fun onEvent(event: ContentContainer.Event) {
            /* no-op */
        }
    }

    fun onEvent(event: ContentContainer.Event)

}