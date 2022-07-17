package com.example.madplayground.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madplayground.R
import com.example.madplayground.features.messages.apis.Message
import com.example.madplayground.features.settings.apis.Settings
import com.example.madplayground.ui.screens.settings.api.SettingsScreen

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    state: SettingsScreen.State = rememberSettingsScreenState(),
    eventHandler: Message.Handler<SettingsScreen.Event>? = null,
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {

        // Title
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        top = 8.dp,
                    )
            ) {

                Text(
                    text = stringResource(id = R.string.title_display),
                    style = MaterialTheme.typography.h4
                )

            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Theme
        item {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        eventHandler?.invoke(
                            SettingsScreen.Event.ThemeTypeClicked
                        )
                    }
            ) {

                Column(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {

                    val typeId = when (state.themeType) {

                        Settings.ThemeType.LIGHT  -> {
                            R.string.label_light
                        }

                        Settings.ThemeType.DARK   -> {
                            R.string.label_dark
                        }

                        Settings.ThemeType.SYSTEM -> {
                            R.string.label_system
                        }

                    }

                    Text(
                        text = stringResource(id = R.string.label_theme_type),
                        style = MaterialTheme.typography.h5
                    )

                    Text(
                        text = stringResource(id = typeId),
                        style = MaterialTheme.typography.body2
                    )

                }

            }

            Spacer(modifier = Modifier.height(16.dp))

        }

        // Icons
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        eventHandler?.invoke(
                            SettingsScreen.Event.IconTypeClicked
                        )
                    }
            ) {

                Column(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {

                    val typeId = when (state.iconType) {

                        Settings.IconographyType.DEFAULT  -> {
                            R.string.label_default
                        }

                        Settings.IconographyType.SHARP    -> {
                            R.string.label_sharp
                        }

                        Settings.IconographyType.OUTLINED -> {
                            R.string.label_outlined
                        }

                        Settings.IconographyType.ROUNDED  -> {
                            R.string.label_rounded
                        }

                        Settings.IconographyType.TWO_TONE -> {
                            R.string.label_two_tone
                        }

                    }

                    Text(
                        text = stringResource(id = R.string.label_icon_type),
                        style = MaterialTheme.typography.h5
                    )

                    Text(
                        text = stringResource(id = typeId),
                        style = MaterialTheme.typography.body2
                    )

                }

            }

            Spacer(modifier = Modifier.height(16.dp))

        }

        // Shapes
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        eventHandler?.invoke(
                            SettingsScreen.Event.ShapeTypeClicked
                        )
                    }
            ) {

                Column(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {

                    val typeId = when (state.shapeType) {

                        Settings.ShapeType.ROUNDED -> {
                            R.string.label_rounded
                        }

                        Settings.ShapeType.CUT     -> {
                            R.string.label_cut
                        }

                    }

                    Text(
                        text = stringResource(id = R.string.label_shape_type),
                        style = MaterialTheme.typography.h5
                    )

                    Text(
                        text = stringResource(id = typeId),
                        style = MaterialTheme.typography.body2
                    )

                }

            }

            Spacer(modifier = Modifier.height(16.dp))

        }

        // Navigation Labels
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        eventHandler?.invoke(
                            SettingsScreen.Event.LabelVisibilityClicked
                        )
                    }
            ) {

                Column(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {

                    val typeId = when (state.alwaysShowNavigationLabels) {

                        true  -> {
                            R.string.label_always_visible
                        }

                        false -> {
                            R.string.label_only_selected
                        }

                    }

                    Text(
                        text = stringResource(id = R.string.title_label_visibility),
                        style = MaterialTheme.typography.h5
                    )

                    Text(
                        text = stringResource(id = typeId),
                        style = MaterialTheme.typography.body2
                    )

                }

            }

        }

    }

    LaunchedEffect(key1 = eventHandler) {
        eventHandler?.invoke(
            SettingsScreen.Event.Started
        )
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun SettingsScreenPreview() {
    val state = rememberSettingsScreenState {

    }

    SettingsScreen(
        state = state,
        modifier = Modifier.fillMaxSize()
    )

}