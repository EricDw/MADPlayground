package com.example.madplayground.app.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.madplayground.cache.database.MomentsRoomDataBase
import com.example.madplayground.cache.moments.mapper.MomentCacheMapper
import com.example.madplayground.cache.moments.models.MomentDao
import com.example.madplayground.cache.moments.source.MomentCacheMapperImpl
import com.example.madplayground.cache.moments.source.MomentsLocalRepositoryImpl
import com.example.madplayground.cache.settings.models.SettingsCache
import com.example.madplayground.cache.settings.source.SettingsCacheImpl
import com.example.madplayground.data.moments.models.MomentsLocalRepository
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
object CacheModule {

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
    fun provideSettingsCache(
        dataStore: DataStore<Preferences>,
    ): SettingsCache {

        val ioScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

        return SettingsCacheImpl(
            scope = ioScope,
            dataStore = dataStore,
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
    ): MomentsLocalRepository {

        return MomentsLocalRepositoryImpl(
            dao = dao,
            mapper = mapper
        )
    }

}
