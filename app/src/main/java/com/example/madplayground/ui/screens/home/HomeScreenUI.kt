package com.example.madplayground.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madplayground.ui.screens.home.api.HomeScreen
import com.example.madplayground.ui.screens.posts.QuoteCard
import com.example.madplayground.ui.screens.posts.QuoteState
import com.example.madplayground.features.quotes.apis.Quote

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreen.State = rememberHomeScreenState(),
    eventHandler: HomeScreen.Event.Handler = HomeScreen.Event.Handler.EMPTY,
) {

    QuoteCardList(
        modifier, state.quotes
    )

    LaunchedEffect(key1 = eventHandler) {

        eventHandler.handle(
            HomeScreen.Event.HomeScreenStarted
        )

    }

}

@Composable
private fun QuoteCardList(
    modifier: Modifier,
    quotes: List<Quote.State>,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 8.dp,
            end = 8.dp
        )
    ) {

        if (quotes.isEmpty()) {
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

        itemsIndexed(
            items = quotes,
            key = { _, quote -> quote.id },
        ) { index, thePost ->

            QuoteCard(
                state = thePost,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = if (index == quotes.lastIndex) {
                            0.dp
                        } else {
                            8.dp
                        }
                    )
            )

        }

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
            QuoteState(id = "$it", title = "Post $it")
        }.also { thePosts ->
            quotes.addAll(thePosts)
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