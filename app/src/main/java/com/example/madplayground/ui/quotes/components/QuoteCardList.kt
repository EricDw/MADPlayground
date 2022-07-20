package com.example.madplayground.ui.quotes.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.madplayground.domain.quotes.models.Quote

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
