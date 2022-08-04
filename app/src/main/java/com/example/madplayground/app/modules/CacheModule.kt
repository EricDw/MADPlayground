package com.example.madplayground.app.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.cache.database.MomentsRoomDataBase
import com.example.cache.moments.mapper.MomentCacheMapper
import com.example.cache.moments.models.MomentDao
import com.example.cache.moments.source.LocalMomentDataSourceImpl
import com.example.cache.moments.source.MomentCacheMapperImpl
import com.example.cache.settings.mapper.SettingsCacheMapper
import com.example.cache.settings.source.LocalSettingsDataSourceImpl
import com.example.cache.settings.source.SettingsCacheMapperImpl
import com.example.core.settings.repository.SettingsCache
import com.example.data.settings.mapper.SettingsDataMapper
import com.example.data.settings.repository.LocalSettingsDataSource
import com.example.data.settings.source.SettingsCacheImpl
import com.example.data.settings.source.SettingsDataMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    fun provideSettingsDataMapper(): SettingsDataMapper {
        return SettingsDataMapperImpl()
    }

    @Provides
    fun provideSettingsCacheMapper(): SettingsCacheMapper {
        return SettingsCacheMapperImpl()
    }

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> {

        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("settings")
        }

    }

    @Provides
    @Singleton
    fun provideMomentRoomDatabase(
        @ApplicationContext context: Context,
    ): MomentsRoomDataBase {

        return Room.databaseBuilder(
            /* context = */ context,
            /* klass = */ MomentsRoomDataBase::class.java,
            /* name = */ "moment_room_database"
        ).build()

    }

    @Provides
    @Singleton
    fun provideLocalSettingsDataSource(
        dataStore: DataStore<Preferences>,
        mapper: SettingsCacheMapper,
        @IODispatcher
        ioDispatcher: CoroutineDispatcher,
    ): LocalSettingsDataSource {

        val ioScope = CoroutineScope(ioDispatcher)

        return LocalSettingsDataSourceImpl(
            dataStore = dataStore,
            mapper = mapper,
            scope = ioScope
        )

    }


    @Provides
    @Singleton
    fun provideSettingsCache(
        dataSource: LocalSettingsDataSource,
        mapper: SettingsDataMapper,
        @IODispatcher
        ioDispatcher: CoroutineDispatcher,
    ): SettingsCache {

        val ioScope = CoroutineScope(ioDispatcher)

        return SettingsCacheImpl(
            localSettingsDataSource = dataSource,
            mapper = mapper,
            scope = ioScope
        )

    }

    @Provides
    @Singleton
    fun provideMomentDao(
        database: MomentsRoomDataBase,
    ): MomentDao {
        return database.momentDao()
    }

    @Provides
    fun provideMomentCacheMapper(): MomentCacheMapper {
        return MomentCacheMapperImpl()
    }

    @Provides
    @Singleton
    fun provideLocalMomentsCache(
        dao: MomentDao,
        mapper: MomentCacheMapper,
    ): com.example.data.moments.models.LocalMomentDataSource {

        return LocalMomentDataSourceImpl(
            dao = dao,
            mapper = mapper
        )
    }

}
