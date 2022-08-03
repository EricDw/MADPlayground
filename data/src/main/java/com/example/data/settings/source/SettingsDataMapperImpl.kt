package com.example.data.settings.source

import com.example.core.settings.models.Settings
import com.example.data.settings.mapper.SettingsDataMapper
import com.example.data.settings.models.IconographyType
import com.example.data.settings.models.NavigationLabelVisibility
import com.example.data.settings.models.ShapeType
import com.example.data.settings.models.ThemeType

class SettingsDataMapperImpl : SettingsDataMapper {

    override fun mapToCore(themeType: ThemeType): Settings.ThemeType {

        return when (themeType) {

            ThemeType.LIGHT  -> {
                Settings.ThemeType.LIGHT
            }

            ThemeType.DARK   -> {
                Settings.ThemeType.DARK
            }

            ThemeType.SYSTEM -> {
                Settings.ThemeType.SYSTEM
            }

        }

    }

    override fun mapToCore(
        shapeType: ShapeType
    ): Settings.ShapeType {

        return when (shapeType) {

            ShapeType.ROUNDED -> {
                Settings.ShapeType.ROUNDED
            }

            ShapeType.CUT     -> {
                Settings.ShapeType.CUT
            }

        }

    }

    override fun mapToCore(
        iconographyType: IconographyType
    ): Settings.IconographyType {

        return when (iconographyType) {

            IconographyType.DEFAULT  -> {
                Settings.IconographyType.DEFAULT
            }

            IconographyType.SHARP    -> {
                Settings.IconographyType.SHARP
            }

            IconographyType.OUTLINED -> {
                Settings.IconographyType.OUTLINED
            }

            IconographyType.ROUNDED  -> {
                Settings.IconographyType.ROUNDED
            }

            IconographyType.TWO_TONE -> {
                Settings.IconographyType.TWO_TONE
            }

        }

    }

    override fun mapToCore(navigationLabelVisibility: NavigationLabelVisibility): Settings.NavigationLabelVisibility {

        return when (navigationLabelVisibility) {

            NavigationLabelVisibility.NEVER         -> {
                Settings.NavigationLabelVisibility.NEVER
            }

            NavigationLabelVisibility.ALWAYS        -> {
                Settings.NavigationLabelVisibility.ALWAYS
            }

            NavigationLabelVisibility.WHEN_SELECTED -> {
                Settings.NavigationLabelVisibility.WHEN_SELECTED
            }

        }

    }


    override fun mapToData(themeType: Settings.ThemeType): ThemeType {
        return when (themeType) {
            Settings.ThemeType.LIGHT  -> {
                ThemeType.LIGHT
            }
            Settings.ThemeType.DARK   -> {
                ThemeType.DARK
            }
            Settings.ThemeType.SYSTEM -> {
                ThemeType.SYSTEM
            }
        }
    }

    override fun mapToData(shapeType: Settings.ShapeType): ShapeType {
        return when (shapeType) {
            Settings.ShapeType.ROUNDED -> {
                ShapeType.ROUNDED
            }
            Settings.ShapeType.CUT     -> {
                ShapeType.CUT
            }
        }
    }

    override fun mapToData(iconographyType: Settings.IconographyType): IconographyType {
        return when (iconographyType) {
            Settings.IconographyType.DEFAULT  -> {
                IconographyType.DEFAULT
            }
            Settings.IconographyType.SHARP    -> {
                IconographyType.SHARP
            }
            Settings.IconographyType.OUTLINED -> {
                IconographyType.OUTLINED
            }
            Settings.IconographyType.ROUNDED  -> {
                IconographyType.ROUNDED
            }
            Settings.IconographyType.TWO_TONE -> {
                IconographyType.TWO_TONE
            }
        }
    }

    override fun mapToData(navigationLabelVisibility: Settings.NavigationLabelVisibility): NavigationLabelVisibility {
        return when (navigationLabelVisibility) {
            Settings.NavigationLabelVisibility.NEVER         -> {
                NavigationLabelVisibility.NEVER
            }
            Settings.NavigationLabelVisibility.ALWAYS        -> {
                NavigationLabelVisibility.ALWAYS
            }
            Settings.NavigationLabelVisibility.WHEN_SELECTED -> {
                NavigationLabelVisibility.WHEN_SELECTED
            }
        }
    }
}