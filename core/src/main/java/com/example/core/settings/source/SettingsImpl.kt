package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.models.Settings.*

class SettingsImpl(
    themeType: ThemeType? = null,
    shapeType: ShapeType? = null,
    iconographyType: IconographyType? = null,
    navigationLabelVisibility: NavigationLabelVisibility? = null,
) : Settings {

    override var themeType: ThemeType = themeType ?: ThemeType.SYSTEM

    override var shapeType: ShapeType = shapeType ?: ShapeType.ROUNDED

    override var iconographyType: IconographyType = iconographyType ?: IconographyType.DEFAULT

    override var navigationLabelVisibility: NavigationLabelVisibility =
        navigationLabelVisibility ?: NavigationLabelVisibility.WHEN_SELECTED

    override fun cycleThemeType() {

        themeType = when (
            themeType
        ) {

            ThemeType.LIGHT  -> {
                ThemeType.DARK
            }

            ThemeType.DARK   -> {
                ThemeType.SYSTEM
            }

            ThemeType.SYSTEM -> {
                ThemeType.LIGHT
            }

        }

    }

    override fun cycleShapeType() {
        shapeType = when (
            shapeType
        ) {

            ShapeType.ROUNDED -> {
                ShapeType.CUT
            }

            ShapeType.CUT     -> {
                ShapeType.ROUNDED
            }

        }

    }

    override fun cycleIconographyType() {

        iconographyType = when (
            iconographyType
        ) {

            IconographyType.DEFAULT  -> {
                IconographyType.SHARP
            }

            IconographyType.SHARP    -> {
                IconographyType.OUTLINED
            }

            IconographyType.OUTLINED -> {
                IconographyType.ROUNDED
            }

            IconographyType.ROUNDED  -> {
                IconographyType.TWO_TONE
            }

            IconographyType.TWO_TONE -> {
                IconographyType.DEFAULT
            }
        }

    }

    override fun cycleNavigationLabelVisibility() {

        navigationLabelVisibility = when (
            navigationLabelVisibility
        ) {

            NavigationLabelVisibility.NEVER         -> {
                NavigationLabelVisibility.ALWAYS
            }

            NavigationLabelVisibility.ALWAYS        -> {
                NavigationLabelVisibility.WHEN_SELECTED
            }

            NavigationLabelVisibility.WHEN_SELECTED -> {
                NavigationLabelVisibility.NEVER
            }

        }

    }

}