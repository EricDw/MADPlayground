package com.example.madplayground.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.madplayground.ui.screens.home.api.HomeScreen
import com.example.madplayground.ui.screens.posts.api.Post

class HomeScreenState : HomeScreen.State {

    override val posts: MutableList<Post.State> = mutableStateListOf()

}

@Composable
fun rememberHomeScreenState(initializer: HomeScreenState.() -> Unit = {}): HomeScreenState =
    remember {
        HomeScreenState().apply(initializer)
    }