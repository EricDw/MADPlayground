package com.example.madplayground.ui.home.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.madplayground.R
import com.example.madplayground.ui.screen.HomeScreen
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun HomeScreenTopAppBar(
    screen: HomeScreen,
    modifier: Modifier = Modifier,
) {

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.title_home)
            )
        },
    )

}