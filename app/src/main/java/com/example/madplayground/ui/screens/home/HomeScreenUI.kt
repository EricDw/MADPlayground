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
import com.example.madplayground.features.quotes.apis.Quote
import com.example.madplayground.ui.components.QuoteCard
import com.example.madplayground.ui.components.QuoteState
import com.example.madplayground.ui.screens.home.api.HomeScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreen.State = rememberHomeScreenState(),
    eventHandler: HomeScreen.Event.Handler = HomeScreen.Event.Handler.EMPTY,
) {

    val quoteOfTheDay = state.quoteOfTheDay

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        if (quoteOfTheDay == null) {

            Text(
                text = "Time to add a quote!"
            )

        } else {

            QuoteCard(
                modifier = Modifier.wrapContentSize(),
                state = quoteOfTheDay
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
            QuoteState(id = "$it", content = "Post $it")
        }.also { theQuotes ->
            quotes.addAll(theQuotes)
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