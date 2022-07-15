package com.example.madplayground.ui.screens.posts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.madplayground.ui.screens.posts.api.Post
import java.util.*

data class PostState(
    override var id: String = UUID.randomUUID().toString(),
    override var title: String = "",
) : Post.State

@Composable
fun rememberPostState(
    initializer: PostState.() -> Unit = {},
): PostState = remember {
    PostState().apply(initializer)
}
