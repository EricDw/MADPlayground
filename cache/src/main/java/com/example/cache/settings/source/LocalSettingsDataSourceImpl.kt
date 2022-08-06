package com.example.cache.settings.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.cache.settings.constants.iconographyKey
import com.example.cache.settings.constants.navigationLabelVisibilityKey
import com.example.cache.settings.constants.shapeKey
import com.example.cache.settings.constants.themeKey
import com.example.cache.settings.mapper.SettingsCacheMapper
import com.example.data.settings.models.SettingsData
import com.example.data.settings.repository.LocalSettingsDataSource
import kotlinx.coroutines.flow.*

class LocalSettingsDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
    private val mapper: SettingsCacheMapper,
) : LocalSettingsDataSource {

    override suspend fun retrieveSettingsData(): SettingsData? {
        return dataStore.data.firstOrNull()?.let(mapper::mapToData)
    }

    override suspend fun updateSettingsData(
        settingsData: SettingsData,
    ) {
        dataStore.edit { preferences ->
            with(settingsData) {
                themeType?.let { preferences[themeKey] = it }
                shapeType?.let { preferences[shapeKey] = it }
                iconographyType?.let { preferences[iconographyKey] = it }
                navigationLabelVisibility?.let {
                    preferences[navigationLabelVisibilityKey] = it
                }
            }
        }
    }

    override fun retrieveSettingsDataFlow(): Flow<SettingsData> {
        return dataStore.data.map(mapper::mapToData)
    }

}