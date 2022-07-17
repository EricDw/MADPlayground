package com.example.madplayground.features.settings.modules

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.madplayground.features.settings.apis.Settings
import com.example.madplayground.features.settings.controllers.SettingsController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettings(
        @ApplicationContext context: Context,
    ): Settings {

        val ioScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

        val dataStore = PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("settings")
        }

        return SettingsController(
            dataStore = dataStore,
            scope = ioScope
        )

    }


}