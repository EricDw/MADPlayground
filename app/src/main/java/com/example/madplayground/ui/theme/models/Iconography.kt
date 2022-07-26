package com.example.madplayground.ui.theme.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.icons.sharp.*
import androidx.compose.material.icons.twotone.*
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import javax.annotation.concurrent.Immutable

@Immutable
data class Iconography(
    val settingsIcon: ImageVector = Icons.Default.Settings,
    val menuIcon: ImageVector = Icons.Default.Menu,
    val homeIcon: ImageVector = Icons.Default.Home,
    val backIcon: ImageVector = Icons.Default.ArrowBack,
    val editIcon: ImageVector = Icons.Default.Edit,
    val dateIcon: ImageVector = Icons.Default.CalendarToday,
    val timeIcon: ImageVector = Icons.Default.Alarm,
    val closeIcon: ImageVector = Icons.Default.Close,
    val timelineIcon: ImageVector = Icons.Default.Timeline,
)

fun defaultIconography() = Iconography()

fun sharpIconography() = Iconography(
    settingsIcon = Icons.Sharp.Settings,
    menuIcon = Icons.Sharp.Menu,
    homeIcon = Icons.Sharp.Home,
    backIcon = Icons.Sharp.ArrowBack,
    editIcon = Icons.Sharp.Edit,
    dateIcon = Icons.Sharp.CalendarToday,
    timeIcon = Icons.Sharp.Alarm,
    timelineIcon = Icons.Sharp.Timeline,
)

fun outlinedIconography() = Iconography(
    settingsIcon = Icons.Outlined.Settings,
    menuIcon = Icons.Outlined.Menu,
    homeIcon = Icons.Outlined.Home,
    backIcon = Icons.Outlined.ArrowBack,
    editIcon = Icons.Outlined.Edit,
    dateIcon = Icons.Outlined.CalendarToday,
    timeIcon = Icons.Outlined.Alarm,
    timelineIcon = Icons.Outlined.Timeline,
)

fun roundedIconography() = Iconography(
    settingsIcon = Icons.Rounded.Settings,
    menuIcon = Icons.Rounded.Menu,
    homeIcon = Icons.Rounded.Home,
    backIcon = Icons.Rounded.ArrowBack,
    editIcon = Icons.Rounded.Edit,
    dateIcon = Icons.Rounded.CalendarToday,
    timeIcon = Icons.Rounded.Alarm,
    timelineIcon = Icons.Rounded.Timeline,
)

fun twoToneIconography() = Iconography(
    settingsIcon = Icons.TwoTone.Settings,
    menuIcon = Icons.TwoTone.Menu,
    homeIcon = Icons.TwoTone.Home,
    backIcon = Icons.TwoTone.ArrowBack,
    editIcon = Icons.TwoTone.Edit,
    dateIcon = Icons.TwoTone.CalendarToday,
    timeIcon = Icons.TwoTone.Alarm,
    timelineIcon = Icons.TwoTone.Timeline,
)

val LocalIconography = compositionLocalOf {
    defaultIconography()
}