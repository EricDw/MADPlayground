package com.example.madplayground.app.modules

import com.example.madplayground.ui.moments.mapper.MomentUIMapper
import com.example.madplayground.ui.moments.source.MomentUIMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UIModule {

    @Provides
    fun provideMomentUIMapper(): MomentUIMapper {
        return MomentUIMapperImpl()
    }

}