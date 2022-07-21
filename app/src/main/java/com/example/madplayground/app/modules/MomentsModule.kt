package com.example.madplayground.app.modules

import com.example.madplayground.domain.moments.models.Moments
import com.example.madplayground.domain.moments.repository.MomentRepository
import com.example.madplayground.domain.moments.source.*
import com.example.madplayground.domain.moments.usecases.CreateMomentUseCase
import com.example.madplayground.domain.moments.usecases.DeleteMomentUseCase
import com.example.madplayground.domain.moments.usecases.RetrieveMomentUseCase
import com.example.madplayground.domain.moments.usecases.UpdateMomentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MomentsModule {

    @Provides
    @Singleton
    fun provideMoments(): Moments {

        return MomentsController()

    }

    @Provides
    fun provideCreateMomentUseCase(
        repository: MomentRepository
    ): CreateMomentUseCase {

        return CreateMomentUseCaseImpl(
            repository = repository
        )

    }

    @Provides
    fun provideRetrieveMomentUseCase(
        repository: MomentRepository
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