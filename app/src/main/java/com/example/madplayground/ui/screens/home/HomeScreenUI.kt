package com.example.madplayground.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.madplayground.ui.components.QuoteCard
import com.example.madplayground.ui.components.QuoteState
import com.example.madplayground.ui.screens.home.api.HomeScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreen.State = rememberHomeScreenState(),
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