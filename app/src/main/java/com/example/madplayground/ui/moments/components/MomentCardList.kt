package com.example.madplayground.ui.moments.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.madplayground.ui.moments.models.MomentUiState

@Composable
fun MomentCardList(
    modifier: Modifier,
    moments: List<MomentUiState>,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 8.dp,
            end = 8.dp
        )
    ) {

        itemsIndexed(
            items = moments,
            key = { _, moment -> moment.id },
        ) { index, theMoment ->

            MomentCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = if (index == moments.lastIndex) {
                            0.dp
                        } else {
                            8.dp
                        }
                    ),
                state = theMoment
            )

        }

    }
}
