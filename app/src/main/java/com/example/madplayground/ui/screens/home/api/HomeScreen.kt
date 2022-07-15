package com.example.madplayground.ui.screens.home.api

import com.example.madplayground.ui.screens.posts.api.Post
import kotlinx.coroutines.flow.StateFlow

interface HomeScreen {

    companion object {

        const val ROUTE: String = "home_screen"

    }

    sealed interface Event {

        fun interface Handler {

            /**
             * Event handler that does nothing.
             */
            object EMPTY : Handler {
                override fun handle(event: Event) {
                    /* no-op */
                }
            }

            fun handle(event: Event)

        }

        object HomeScreenStarted : Event

    }

    interface State {

        val posts: List<Post.State>

    }

    interface ViewModel : Event.Handler {

        val state: StateFlow<State>

    }

}