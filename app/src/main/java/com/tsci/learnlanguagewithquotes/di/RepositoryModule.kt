package com.tsci.learnlanguagewithquotes.di

import com.tsci.learnlanguagewithquotes.data.repository.QuoteRepository
import com.tsci.learnlanguagewithquotes.data.repository.QuoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindQuoteRepository(quoteRepositoryImpl: QuoteRepositoryImpl): QuoteRepository
}