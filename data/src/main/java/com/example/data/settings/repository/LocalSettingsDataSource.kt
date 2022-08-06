package com.example.data.settings.repository

import com.example.data.settings.models.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LocalSettingsDataSource {

    suspend fun retrieveSettingsData(): SettingsData?

    suspend fun updateSettingsData(
        settingsData: SettingsData
    )

    fun retrieveSettingsDataFlow(): Flow<SettingsData>

}