package com.example.madplayground.app.modules

import com.example.madplayground.data.moments.mapper.MomentDataMapper
import com.example.madplayground.data.moments.mapper.MomentDataMapperImpl
import com.example.madplayground.data.moments.models.LocalMomentDataSource
import com.example.madplayground.data.moments.repository.MomentRepositoryImpl
import com.example.madplayground.domain.moments.repository.MomentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideMomentsDataMapper(): MomentDataMapper {
        return MomentDataMapperImpl()
    }

    @Provides
    @Singleton
    fun provideMomentsRepository(
        localMomentDataSource: LocalMomentDataSource,
        mapper: MomentDataMapper,
    ): MomentRepository {
        return MomentRepositoryImpl(
            localMomentDataSource = localMomentDataSource,
            mapper = mapper
        )
    }

}
