package com.example.madplayground.features.settings.controllers

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.madplayground.features.settings.apis.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

class SettingsController(
    private val dataStore: DataStore<Preferences>,
    private val scope: CoroutineScope,
) : Settings {


    private val themeKey = stringPreferencesKey("theme_type")
    private val iconographyKey = stringPreferencesKey("iconography_type")
    private val shapeKey = stringPreferencesKey("shape_type")
    private val showNavigationLabelKey = booleanPreferencesKey("show_navigation_labels")

    private val _themeType = MutableStateFlow(
        Settings.ThemeType.SYSTEM
    )

    private val _iconographyType = MutableStateFlow(
        Settings.IconographyType.DEFAULT
    )

    private val _shapeType = MutableStateFlow(
        Settings.ShapeType.ROUNDED
    )

    private val _alwaysShowNavigationLabels = MutableStateFlow(
        false
    )

    override val themeType: StateFlow<Settings.ThemeType> =
        _themeType.asStateFlow()

    override val iconographyType: StateFlow<Settings.IconographyType> =
        _iconographyType.asStateFlow()

    override val shapeType: StateFlow<Settings.ShapeType> =
        _shapeType.asStateFlow()

    override val alwaysShowNavigationLabels: StateFlow<Boolean> =
        _alwaysShowNavigationLabels.asStateFlow()


    init {

        with(dataStore) {
            data.mapTo(_themeType, scope) {
                it[themeKey]?.let { name ->
                    Settings.ThemeType.valueOf(name)
                } ?: Settings.ThemeType.SYSTEM
            }

            data.mapTo(_iconographyType, scope) {
                it[iconographyKey]?.let { name ->
                    Settings.IconographyType.valueOf(name)
                } ?: Settings.IconographyType.DEFAULT
            }

            data.mapTo(_shapeType, scope) {
                it[shapeKey]?.let { name ->
                    Settings.ShapeType.valueOf(name)
                } ?: Settings.ShapeType.ROUNDED
            }

            data.mapTo(_alwaysShowNavigationLabels, scope) {
                it[showNavigationLabelKey] ?: false
            }
        }

    }


    override suspend fun setThemeType(
        newThemeType: Settings.ThemeType,
    ) {
        dataStore.edit { preferences ->
            preferences[themeKey] = newThemeType.name
        }
    }

    override suspend fun setIconographyType(
        newIconographyType: Settings.IconographyType,
    ) {
        dataStore.edit { preferences ->
            preferences[iconographyKey] = newIconographyType.name
        }
    }

    override suspend fun setShapeType(
        newShapeType: Settings.ShapeType,
    ) {
        dataStore.edit { preferences ->
            preferences[shapeKey] = newShapeType.name
        }
    }

    override suspend fun setAlwaysShowNavigationLabels(
        showLabels: Boolean
    ) {
        dataStore.edit { preferences ->
            preferences[showNavigationLabelKey] = showLabels
        }
    }
}

private fun <A, B> Flow<A>.mapTo(
    other: MutableSharedFlow<B>, scope: CoroutineScope, mapper: suspend (A) -> B,
): Job {
    return map(mapper).onEach(other::emit).launchIn(scope)
}