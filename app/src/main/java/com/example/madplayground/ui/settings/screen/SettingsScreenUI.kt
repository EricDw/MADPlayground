package com.example.madplayground.features.settings.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madplayground.R
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.settings.models.SettingsScreen

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    state: SettingsScreen.State = rememberSettingsScreenState(),
    eventHandler: Message.Handler<SettingsScreen.Event>? = null,
) {

    val themeType by state.themeType.collectAsState()
    val iconType by state.iconType.collectAsState()
    val shapeType by state.shapeType.collectAsState()
    val navigationLabelVisibility by state.navigationLabelVisibility.collectAsState()

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

            val labelId = when (themeType) {

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

            SettingItem(
                titleId = R.string.title_theme_type,
                labelId = labelId
            ) {
                eventHandler?.invoke(
                    SettingsScreen.Event.ThemeTypeClicked
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

        }

        // Icons
        item {

            val labelId = when (iconType) {

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

            SettingItem(
                titleId = R.string.title_icon_type,
                labelId = labelId
            ) {
                eventHandler?.invoke(
                    SettingsScreen.Event.IconTypeClicked
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

        }

        // Shapes
        item {

            val labelId = when (shapeType) {

                Settings.ShapeType.ROUNDED -> {
                    R.string.label_rounded
                }

                Settings.ShapeType.CUT     -> {
                    R.string.label_cut
                }

            }

            SettingItem(
                titleId = R.string.title_shape_type,
                labelId = labelId
            ) {
                eventHandler?.invoke(
                    SettingsScreen.Event.ShapeTypeClicked
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

        }

        // Navigation Labels
        item {

            val labelId = when (navigationLabelVisibility) {

                Settings.NavigationLabelVisibility.ALWAYS        -> {
                    R.string.label_always_visible
                }

                Settings.NavigationLabelVisibility.WHEN_SELECTED -> {
                    R.string.label_when_selected
                }

                Settings.NavigationLabelVisibility.NEVER         -> {
                    R.string.label_never
                }

            }

            SettingItem(
                titleId = R.string.title_label_visibility,
                labelId = labelId
            ) {
                eventHandler?.invoke(
                    SettingsScreen.Event.LabelVisibilityClicked
                )
            }

        }

    }

}

@Composable
private fun SettingItem(
    @StringRes
    titleId: Int,
    @StringRes
    labelId: Int,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {

        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        ) {

            Text(
                text = stringResource(id = titleId),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = stringResource(id = labelId),
                style = MaterialTheme.typography.body2
            )

        }

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