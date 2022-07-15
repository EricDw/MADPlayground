package com.example.madplayground.ui.screens.posts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madplayground.ui.screens.posts.api.Post

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    state: Post.State = rememberPostState(),
) {
    Card(
        modifier = modifier,
        elevation = 1.dp
    ) {

        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = state.title
            )

        }

    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun PostCardPreview() {

    val state = rememberPostState {
        title = "Hello World"
    }

    PostCard(
        state = state
    )
}