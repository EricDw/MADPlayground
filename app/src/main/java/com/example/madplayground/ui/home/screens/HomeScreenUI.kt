package com.example.madplayground.features.home

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
import com.example.madplayground.ui.quotes.components.MomentCard
import com.example.madplayground.ui.quotes.components.MomentState
import com.example.madplayground.ui.home.models.HomeScreen
import com.example.madplayground.ui.home.source.rememberHomeScreenState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreen.State = rememberHomeScreenState(),
) {

    val momentOfTheDay = state.momentOfTheDay

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        if (momentOfTheDay == null) {

            Text(
                text = stringResource(id = R.string.message_add_moment)
            )

        } else {

            MomentCard(
                modifier = Modifier.wrapContentSize(),
                state = momentOfTheDay
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
            MomentState(id = "$it", description = "Post $it")
        }.also { theMoments ->
            moments.addAll(theMoments)
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