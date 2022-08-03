package com.example.data.settings.source

import com.example.core.settings.models.Settings.*
import com.example.core.settings.repository.SettingsCache
import com.example.data.settings.mapper.SettingsDataMapper
import com.example.data.settings.repository.LocalSettingsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

class SettingsCacheImpl(
    private val localSettingsDataSource: LocalSettingsDataSource,
    private val mapper: SettingsDataMapper,
    private val scope: CoroutineScope
) : SettingsCache {

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

        with(localSettingsDataSource) {

            themeType.mapTo(_themeType, scope, mapper::mapToCore)

            shapeType.mapTo(_shapeType, scope, mapper::mapToCore)

            iconographyType.mapTo(_iconographyType, scope, mapper::mapToCore)

            navigationLabelVisibility.mapTo(_navigationLabelVisibility, scope, mapper::mapToCore)

        }

    }


    override suspend fun setThemeType(
        newThemeType: ThemeType,
    ) {
        localSettingsDataSource.setThemeType(
            mapper.mapToData(newThemeType)
        )
    }

    override suspend fun setIconographyType(
        newIconographyType: IconographyType,
    ) {
        localSettingsDataSource.setIconographyType(
            mapper.mapToData(newIconographyType)
        )
    }

    override suspend fun setShapeType(
        newShapeType: ShapeType,
    ) {
        localSettingsDataSource.setShapeType(
            mapper.mapToData(newShapeType)
        )
    }

    override suspend fun setNavigationLabelVisibility(
        newVisibility: NavigationLabelVisibility,
    ) {
        localSettingsDataSource.setNavigationLabelVisibility(
            mapper.mapToData(newVisibility)
        )
    }
}

private fun <A, B> Flow<A>.mapTo(
    other: MutableSharedFlow<B>, scope: CoroutineScope, mapper: suspend (A) -> B,
): Job {
    return map(mapper).onEach(other::emit).launchIn(scope)
}