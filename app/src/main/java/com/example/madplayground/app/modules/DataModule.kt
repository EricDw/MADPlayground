package com.example.madplayground.app.modules

import com.example.madplayground.data.moments.models.MomentsLocalRepository
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
    @Singleton
    fun provideMomentsRepository(
        localRepository: MomentsLocalRepository
    ): MomentRepository {
        return MomentRepositoryImpl(
            localRepository = localRepository
        )
    }

}
