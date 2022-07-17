package com.example.madplayground.ui.screens.posts

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madplayground.features.quotes.apis.Quote

@Composable
fun QuoteCard(
    modifier: Modifier = Modifier,
    state: Quote.State = rememberQuoteState(),
) {
    Card(
        modifier = modifier,
        elevation = 1.dp
    ) {

        Column(
            modifier = Modifier.padding(16.dp).wrapContentHeight(),
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "\"",
                    style = MaterialTheme.typography.h3
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = state.title
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "\"",
                    style = MaterialTheme.typography.h3
                )
            }

        }

    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun PostCardPreview() {

    val state = rememberQuoteState {
        title = "Hello World"
    }

    QuoteCard(
        state = state
    )
}