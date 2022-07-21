package com.example.madplayground.ui.quotes.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.madplayground.ui.quotes.models.MomentUiState

@Composable
private fun MomentCardList(
    modifier: Modifier,
    moments: List<MomentUiState>,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 8.dp,
            end = 8.dp
        )
    ) {

        if (moments.isEmpty()) {
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
            items = moments,
            key = { _, moment -> moment.id },
        ) { index, theMoment ->

            MomentCard(
                state = theMoment,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = if (index == moments.lastIndex) {
                            0.dp
                        } else {
                            8.dp
                        }
                    )
            )

        }

    }
}