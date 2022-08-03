package com.example.madplayground.app.modules

import com.example.core.moments.repository.MomentRepository
import com.example.core.moments.source.*
import com.example.core.moments.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MomentsModule {

    @Provides
    fun provideCreateMomentUseCase(
        repository: MomentRepository,
    ): CreateMomentUseCase {

        return CreateMomentUseCaseImpl(
            repository = repository
        )

    }

    @Provides
    fun provideRetrieveMomentUseCase(
        repository: MomentRepository,
    ): RetrieveMomentUseCase {

        return RetrieveMomentUseCaseImpl(
            repository = repository
        )

    }

    @Provides
    fun provideRetrieveAllMomentUseCase(
        repository: MomentRepository,
    ): RetrieveAllMomentUseCase {

        return RetrieveAllMomentUseCaseImpl(
            repository = repository
        )

    }

    @Provides
    fun provideUpdateMomentUseCase(): UpdateMomentUseCase {

        return UpdateMomentUseCaseImpl()

    }

    @Provides
    fun provideDeleteMomentUseCase(): DeleteMomentUseCase {

        return DeleteMomentUseCaseImpl()

    }

}