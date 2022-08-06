package com.example.data.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsRepository
import com.example.core.settings.source.SettingsImpl
import com.example.data.settings.mapper.SettingsDataMapper
import com.example.data.settings.repository.LocalSettingsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

class SettingsRepositoryImpl(
    private val localSettingsDataSource: LocalSettingsDataSource,
    private val mapper: SettingsDataMapper,
    private val scope: CoroutineScope,
) : SettingsRepository {

    override suspend fun retrieveSettings(): Settings {
        return localSettingsDataSource.retrieveSettingsData()
            ?.let(mapper::mapToCore)
            ?: SettingsImpl()
    }

    override suspend fun updateSettings(settings: Settings) {
        localSettingsDataSource.updateSettingsData(
            mapper.mapToData(settings)
        )
    }

    override fun retrieveSettingsFlow(): Flow<Settings> {
        return localSettingsDataSource.retrieveSettingsDataFlow().map(mapper::mapToCore)
    }


}

private fun <A, B> Flow<A>.mapTo(
    other: MutableSharedFlow<B>, scope: CoroutineScope, mapper: suspend (A) -> B,
): Job {
    return map(mapper).onEach(other::emit).launchIn(scope)
}