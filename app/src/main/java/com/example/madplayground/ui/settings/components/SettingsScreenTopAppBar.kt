package com.example.madplayground.ui.settings.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.madplayground.R
import com.example.madplayground.ui.screen.SettingsScreen
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun SettingsScreenTopAppBar(
    screen: SettingsScreen,
    modifier: Modifier = Modifier,
) {

    val iconography = LocalIconography.current

    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = {
                    screen.onEvent(
                        SettingsScreen.Event.BackClicked
                    )
                },
            ) {

                Icon(
                    imageVector = iconography.backIcon,
                    contentDescription = stringResource(id = R.string.description_go_back)
                )

            }

        },
        title = {
            Text(
                text = stringResource(id = R.string.title_settings)
            )
        },
    )

}