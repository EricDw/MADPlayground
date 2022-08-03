package com.example.madplayground.app.modules

import com.example.core.moments.repository.MomentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideMomentsDataMapper(): com.example.data.moments.mapper.MomentDataMapper {
        return com.example.data.moments.mapper.MomentDataMapperImpl()
    }

    @Provides
    @Singleton
    fun provideMomentsRepository(
        localMomentDataSource: com.example.data.moments.models.LocalMomentDataSource,
        mapper: com.example.data.moments.mapper.MomentDataMapper,
    ): MomentRepository {
        return com.example.data.moments.repository.MomentRepositoryImpl(
            localMomentDataSource = localMomentDataSource,
            mapper = mapper
        )
    }

}
