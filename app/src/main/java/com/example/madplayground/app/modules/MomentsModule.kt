package com.example.madplayground.app.modules

import com.example.madplayground.domain.moments.repository.MomentRepository
import com.example.madplayground.domain.moments.source.CreateMomentUseCaseImpl
import com.example.madplayground.domain.moments.source.DeleteMomentUseCaseImpl
import com.example.madplayground.domain.moments.source.RetrieveMomentUseCaseImpl
import com.example.madplayground.domain.moments.source.UpdateMomentUseCaseImpl
import com.example.madplayground.domain.moments.usecases.CreateMomentUseCase
import com.example.madplayground.domain.moments.usecases.DeleteMomentUseCase
import com.example.madplayground.domain.moments.usecases.RetrieveMomentUseCase
import com.example.madplayground.domain.moments.usecases.UpdateMomentUseCase
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
    fun provideUpdateMomentUseCase(): UpdateMomentUseCase {

        return UpdateMomentUseCaseImpl()

    }

    @Provides
    fun provideDeleteMomentUseCase(): DeleteMomentUseCase {

        return DeleteMomentUseCaseImpl()

    }

}