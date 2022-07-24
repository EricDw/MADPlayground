package com.example.madplayground.ui.home.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.madplayground.R
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.screen.HomeScreen
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun HomeScreenFAB(
    screen: HomeScreen,
    modifier: Modifier = Modifier,
) {

    FloatingActionButton(
        onClick = {
            screen.onEvent(
                ContentContainer.Event.FABClicked
            )
        },
        modifier = modifier,
    ) {
        Icon(
            imageVector = LocalIconography.current.editIcon,
            contentDescription = stringResource(id = R.string.description_add_moment)
        )
    }

}