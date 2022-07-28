package com.example.madplayground.ui.moments.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madplayground.ui.moments.models.MomentUiState
import com.example.madplayground.ui.moments.source.rememberMomentUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MomentCard(
    modifier: Modifier = Modifier,
    state: MomentUiState = rememberMomentUiState(),
) {
    ElevatedCard(
        modifier = modifier,
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
        modifier = Modifier.wrapContentSize(),
        state = state
    )
}