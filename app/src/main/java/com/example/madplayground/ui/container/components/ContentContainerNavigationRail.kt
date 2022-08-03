package com.example.madplayground.ui.container.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.core.settings.models.Settings
import com.example.madplayground.R
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun ContentContainer.ContentContainerNavigationRail(
    modifier: Modifier = Modifier,
    navigationLabelVisibility: Settings.NavigationLabelVisibility = Settings.NavigationLabelVisibility.WHEN_SELECTED,
) {

    AnimatedVisibility(
        showNavigationRail,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally()
    ) {
        NavigationRail(
            modifier = modifier.fillMaxHeight(),
            header = railHeader,
        ) {

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {

                RailItem(
                    isSelected = isTimelineSelected,
                    imageVector = LocalIconography.current.timelineIcon,
                    descriptionId = R.string.description_timeline_tab,
                    navigationLabelVisibility = navigationLabelVisibility,
                    textId = R.string.title_timeline,
                    onClick = onTimelineClick
                )

                RailItem(
                    isSelected = isSettingsSelected,
                    imageVector = LocalIconography.current.settingsIcon,
                    descriptionId = R.string.description_settings_tab,
                    navigationLabelVisibility = navigationLabelVisibility,
                    textId = R.string.title_settings,
                    onClick = onSettingClick
                )

            }

        }
    }

}

@Composable
private fun RailItem(
    imageVector: ImageVector,
    @StringRes
    descriptionId: Int,
    isSelected: Boolean,
    navigationLabelVisibility: Settings.NavigationLabelVisibility,
    textId: Int,
    onClick: () -> Unit,
) {
    NavigationRailItem(
        selected = isSelected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = imageVector,
                contentDescription = stringResource(id = descriptionId)
            )
        },
        label = if (navigationLabelVisibility == Settings.NavigationLabelVisibility.NEVER) {
            null
        } else {
            {
                Text(
                    text = stringResource(id = textId)
                )
            }
        },
        alwaysShowLabel = navigationLabelVisibility == Settings.NavigationLabelVisibility.ALWAYS
    )
}