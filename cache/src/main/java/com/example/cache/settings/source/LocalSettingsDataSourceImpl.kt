package com.example.cache.settings.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.cache.settings.mapper.SettingsCacheMapper
import com.example.data.settings.models.IconographyType
import com.example.data.settings.models.NavigationLabelVisibility
import com.example.data.settings.models.ShapeType
import com.example.data.settings.models.ThemeType
import com.example.data.settings.repository.LocalSettingsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

class LocalSettingsDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
    private val mapper: SettingsCacheMapper,
    private val scope: CoroutineScope,
) : LocalSettingsDataSource {

    private val themeKey = stringPreferencesKey("theme_type")
    private val iconographyKey = stringPreferencesKey("iconography_type")
    private val shapeKey = stringPreferencesKey("shape_type")
    private val navigationLabelVisibilityKey = stringPreferencesKey("navigation_visibility")

    private val _themeType = MutableStateFlow(
        ThemeType.SYSTEM
    )

    private val _iconographyType = MutableStateFlow(
        IconographyType.DEFAULT
    )

    private val _shapeType = MutableStateFlow(
        ShapeType.ROUNDED
    )

    private val _navigationLabelVisibility = MutableStateFlow(
        NavigationLabelVisibility.WHEN_SELECTED
    )

    override val themeType: StateFlow<ThemeType> =
        _themeType.asStateFlow()

    override val iconographyType: StateFlow<IconographyType> =
        _iconographyType.asStateFlow()

    override val shapeType: StateFlow<ShapeType> =
        _shapeType.asStateFlow()

    override val navigationLabelVisibility: StateFlow<NavigationLabelVisibility> =
        _navigationLabelVisibility.asStateFlow()


    init {

        with(dataStore) {
            data.mapTo(_themeType, scope) {
                it[themeKey]?.let { name ->
                    ThemeType.valueOf(name)
                } ?: ThemeType.SYSTEM
            }

            data.mapTo(_iconographyType, scope) {
                it[iconographyKey]?.let { name ->
                    IconographyType.valueOf(name)
                } ?: IconographyType.DEFAULT
            }

            data.mapTo(_shapeType, scope) {
                it[shapeKey]?.let { name ->
                    ShapeType.valueOf(name)
                } ?: ShapeType.ROUNDED
            }

            data.mapTo(_navigationLabelVisibility, scope) {
                it[navigationLabelVisibilityKey]?.let { name ->
                    NavigationLabelVisibility.valueOf(name)
                } ?: NavigationLabelVisibility.WHEN_SELECTED
            }
        }

    }


    override suspend fun setThemeType(
        newThemeType: ThemeType,
    ) {
        dataStore.edit { preferences ->
            preferences[themeKey] = mapper.mapToCache(newThemeType)
        }
    }

    override suspend fun setIconographyType(
        newIconographyType: IconographyType,
    ) {
        dataStore.edit { preferences ->
            preferences[iconographyKey] = newIconographyType.name
        }
    }

    override suspend fun setShapeType(
        newShapeType: ShapeType,
    ) {
        dataStore.edit { preferences ->
            preferences[shapeKey] = newShapeType.name
        }
    }

    override suspend fun setNavigationLabelVisibility(
        newVisibility: NavigationLabelVisibility,
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