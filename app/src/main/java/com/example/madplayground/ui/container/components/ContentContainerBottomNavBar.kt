package com.example.madplayground.ui.container.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.madplayground.R
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.screen.HomeScreen
import com.example.madplayground.ui.screen.SettingsScreen
import com.example.madplayground.ui.theme.models.LocalIconography

@Composable
fun ContentContainerBottomNavBar(
    contentContainer: ContentContainer,
    modifier: Modifier = Modifier,
) {

    val screen = contentContainer.currentScreen

    val navigationLabelVisibility = contentContainer.state.navigationLabelVisibility

    val alwaysShowLabel = navigationLabelVisibility == Settings.NavigationLabelVisibility.ALWAYS

    val isHomeSelected = screen is HomeScreen

    val isSettingsSelected = screen is SettingsScreen

    AnimatedVisibility(
        contentContainer.showBottomNavBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {

        BottomNavigation(
            modifier = modifier,
        ) {

            BottomNavigationItem(
                selected = isHomeSelected,
                onClick = {
                    screen.onEvent(
                        ContentContainer.Event.HomeTabClicked
                    )
                },
                icon = {
                    Icon(
                        imageVector = LocalIconography.current.homeIcon,
                        contentDescription = "Home Tab"
                    )
                },
                label = if (navigationLabelVisibility == Settings.NavigationLabelVisibility.NEVER) {
                    null
                } else {
                    {
                        Text(
                            text = stringResource(id = R.string.title_home)
                        )
                    }
                },
                alwaysShowLabel = alwaysShowLabel
            )

            BottomNavigationItem(
                selected = isSettingsSelected,
                onClick = {
                    screen.onEvent(
                        ContentContainer.Event.SettingsTabClicked
                    )
                },
                icon = {
                    Icon(
                        imageVector = LocalIconography.current.settingsIcon,
                        contentDescription = "Settings Tab"
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