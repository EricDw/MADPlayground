package com.example.madplayground.ui.container.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.settings.models.Settings
import com.example.madplayground.R
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun ContentContainer.ContentContainerBottomNavBar(
    modifier: Modifier = Modifier,
) {

    val navigationLabelVisibility by state.navigationLabelVisibility.collectAsState()

    val alwaysShowLabel = navigationLabelVisibility == Settings.NavigationLabelVisibility.ALWAYS

    AnimatedVisibility(
        showBottomNavBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {

        NavigationBar(
            modifier = modifier,
        ) {

            NavigationBarItem(
                selected = isTimelineSelected,
                onClick = onTimelineClick,
                icon = {
                    Icon(
                        imageVector = LocalIconography.current.timelineIcon,
                        contentDescription = stringResource(id = R.string.description_timeline_tab)
                    )
                },
                label = if (navigationLabelVisibility == Settings.NavigationLabelVisibility.NEVER) {
                    null
                } else {
                    {
                        Text(
                            text = stringResource(id = R.string.title_timeline)
                        )
                    }
                },
                alwaysShowLabel = alwaysShowLabel
            )

            NavigationBarItem(
                selected = isSettingsSelected,
                onClick = onSettingClick,
                icon = {
                    Icon(
                        imageVector = LocalIconography.current.settingsIcon,
                        contentDescription = stringResource(id = R.string.description_settings_tab)
                    )
                },
                label = if (navigationLabelVisibility == Settings.NavigationLabelVisibility.NEVER) {
                    null
                } else {
                    {
                        Text(
                            text = stringResource(id = R.string.title_settings)
                        )
                    }
                },
                alwaysShowLabel = alwaysShowLabel
            )
        }
    }

}