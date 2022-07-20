package com.example.madplayground.app.modules

import com.example.madplayground.domain.quotes.models.Quotes
import com.example.madplayground.domain.quotes.source.QuotesController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuotesModule {

    @Provides
    @Singleton
    fun provideQuotes(): Quotes {

        return QuotesController()

    }


}