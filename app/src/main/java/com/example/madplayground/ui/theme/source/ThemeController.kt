package com.example.madplayground.ui.theme.source

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.ui.theme.models.*

private val DarkColorScheme = darkColorScheme()

private val LightColorScheme = lightColorScheme()

@Composable
fun ThemeController(
    themeType: Settings.ThemeType = Settings.ThemeType.SYSTEM,
    iconographyType: Settings.IconographyType = Settings.IconographyType.DEFAULT,
    shapeType: Settings.ShapeType = Settings.ShapeType.ROUNDED,
    content: @Composable () -> Unit,
) {

    val colorScheme = when (themeType) {
        Settings.ThemeType.LIGHT  -> {
            LightColorScheme
        }
        Settings.ThemeType.DARK   -> {
            DarkColorScheme
        }
        Settings.ThemeType.SYSTEM -> {
            if (isSystemInDarkTheme()) {
                DarkColorScheme
            } else {
                LightColorScheme
            }
        }
    }

    val iconography = when (iconographyType) {

        Settings.IconographyType.DEFAULT  -> {
            defaultIconography()
        }

        Settings.IconographyType.SHARP    -> {
            sharpIconography()
        }

        Settings.IconographyType.OUTLINED -> {
            outlinedIconography()
        }

        Settings.IconographyType.ROUNDED  -> {
            roundedIconography()
        }

        Settings.IconographyType.TWO_TONE -> {
            twoToneIconography()
        }

    }

    val shapes = when (shapeType) {
        Settings.ShapeType.ROUNDED -> {
            RoundedShapes
        }
        Settings.ShapeType.CUT     -> {
            CutShapes
        }
    }

    CompositionLocalProvider(
        LocalIconography provides iconography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = shapes,
            content = content
        )
    }

}