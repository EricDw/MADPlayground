package com.example.madplayground.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madplayground.ui.screens.home.api.HomeScreen
import com.example.madplayground.ui.screens.posts.PostCard
import com.example.madplayground.ui.screens.posts.PostState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreen.State = rememberHomeScreenState(),
    eventHandler: HomeScreen.Event.Handler = HomeScreen.Event.Handler.EMPTY,
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 8.dp,
            end = 8.dp
        )
    ) {

        if (state.posts.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Home Screen",
                    )
                }
            }
        }

        items(
            items = state.posts,
            key = { it.id }
        ) { thePost ->

            PostCard(
                state = thePost,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

        }

    }

    LaunchedEffect(key1 = eventHandler) {

        eventHandler.handle(
            HomeScreen.Event.HomeScreenStarted
        )

    }

}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun HomeScreenPreview() {

    val state = rememberHomeScreenState {

        (0..5).map {
            PostState(id = "$it", title = "Post $it")
        }.also { thePosts ->
            posts.addAll(thePosts)
        }

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { scaffoldPadding ->

        HomeScreen(
            modifier = Modifier.padding(scaffoldPadding),
            state = state,
        )

    }

}