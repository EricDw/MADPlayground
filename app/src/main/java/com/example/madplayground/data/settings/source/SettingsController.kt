package com.example.madplayground.data.settings.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.madplayground.domain.settings.models.Settings
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
    private val navigationLabelVisibilityKey = stringPreferencesKey("navigation_visibility")

    private val _themeType = MutableStateFlow(
        Settings.ThemeType.SYSTEM
    )

    private val _iconographyType = MutableStateFlow(
        Settings.IconographyType.DEFAULT
    )

    private val _shapeType = MutableStateFlow(
        Settings.ShapeType.ROUNDED
    )

    private val _navigationLabelVisibility = MutableStateFlow(
        Settings.NavigationLabelVisibility.WHEN_SELECTED
    )

    override val themeType: StateFlow<Settings.ThemeType> =
        _themeType.asStateFlow()

    override val iconographyType: StateFlow<Settings.IconographyType> =
        _iconographyType.asStateFlow()

    override val shapeType: StateFlow<Settings.ShapeType> =
        _shapeType.asStateFlow()

    override val navigationLabelVisibility: StateFlow<Settings.NavigationLabelVisibility> =
        _navigationLabelVisibility.asStateFlow()


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

            data.mapTo(_navigationLabelVisibility, scope) {
                it[navigationLabelVisibilityKey]?.let { name ->
                    Settings.NavigationLabelVisibility.valueOf(name)
                } ?: Settings.NavigationLabelVisibility.WHEN_SELECTED
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

    override suspend fun setNavigationLabelVisibility(
        newVisibility: Settings.NavigationLabelVisibility,
    ) {
        dataStore.edit { preferences ->
            preferences[navigationLabelVisibilityKey] = newVisibility.name
        }
    }
}

private fun <A, B> Flow<A>.mapTo(
    other: MutableSharedFlow<B>, scope: CoroutineScope, mapper: suspend (A) -> B,
): Job {
    return map(mapper).onEach(other::emit).launchIn(scope)
}