package com.example.madplayground.ui.quotes.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.madplayground.domain.quotes.models.Quote

@Composable
fun QuoteCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    state: Quote.State = rememberQuoteState(),
) {
    Card(
        modifier = modifier,
        elevation = elevation,
    ) {

        Box(
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = state.content,
                modifier = Modifier.padding(16.dp)
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

    val state = rememberQuoteState {
        content = "Hello World"
    }

    QuoteCard(
        state = state,
        elevation = 4.dp,
        modifier = Modifier.wrapContentSize()
    )
}