package com.example.madplayground.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.madplayground.features.settings.apis.Settings

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AppTheme(
    themeType: Settings.ThemeType = Settings.ThemeType.SYSTEM,
    iconographyType: Settings.IconographyType = Settings.IconographyType.DEFAULT,
    shapeType: Settings.ShapeType = Settings.ShapeType.ROUNDED,
    content: @Composable () -> Unit,
) {

    val colors = when (themeType) {
        Settings.ThemeType.LIGHT  -> {
            LightColorPalette
        }
        Settings.ThemeType.DARK   -> {
            DarkColorPalette
        }
        Settings.ThemeType.SYSTEM -> {
            if (isSystemInDarkTheme()) {
                DarkColorPalette
            } else {
                LightColorPalette
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
            colors = colors,
            typography = Typography,
            shapes = shapes,
            content = content
        )
    }

}