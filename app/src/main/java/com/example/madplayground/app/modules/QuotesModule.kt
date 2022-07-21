package com.example.madplayground.app.modules

import com.example.madplayground.domain.moments.models.Moments
import com.example.madplayground.domain.moments.source.MomentsController
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


}