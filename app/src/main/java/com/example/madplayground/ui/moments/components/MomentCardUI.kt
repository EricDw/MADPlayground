package com.example.madplayground.ui.moments.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.madplayground.ui.moments.models.MomentUiState
import com.example.madplayground.ui.moments.source.rememberMomentUiState

@Composable
fun MomentCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    state: MomentUiState = rememberMomentUiState(),
) {
    Card(
        modifier = modifier,
        elevation = elevation,
    ) {

        Box(
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = state.description,
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

    val state = rememberMomentUiState {
        description = "Hello World"
    }

    MomentCard(
        state = state,
        elevation = 4.dp,
        modifier = Modifier.wrapContentSize()
    )
}