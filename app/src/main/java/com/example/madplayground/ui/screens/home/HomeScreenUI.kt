package com.example.madplayground.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.madplayground.R
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
                text = stringResource(id = R.string.message_add_quote)
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