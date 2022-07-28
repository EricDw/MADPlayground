package com.example.madplayground.ui.timeline.source

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.madplayground.ui.moments.components.MomentCardList
import com.example.madplayground.ui.moments.source.MomentUiStateImpl
import com.example.madplayground.ui.timeline.models.TimelineScreen

@Composable
fun TimelineScreen(
    modifier: Modifier = Modifier,
    state: TimelineScreen.State = rememberTimelineScreenState(),
) {

    MomentCardList(
        modifier = modifier,
        moments = state.moments
    )

}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun TimelineScreenPreview() {

    val state = rememberTimelineScreenState {

        (0..5).map {
            MomentUiStateImpl(id = "$it", description = "Post $it")
        }.also { theMoments ->
            moments.addAll(theMoments)
        }

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { scaffoldPadding ->

        TimelineScreen(
            modifier = Modifier.padding(scaffoldPadding),
            state = state,
        )

    }

}