package com.example.madplayground.features.quotes.modules

import com.example.madplayground.features.quotes.apis.Quotes
import com.example.madplayground.features.quotes.controllers.QuotesController
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