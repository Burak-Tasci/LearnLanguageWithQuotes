package com.tsci.learnlanguagewithquotes.di

import com.tsci.learnlanguagewithquotes.data.repository.QuoteRepository
import com.tsci.learnlanguagewithquotes.domain.mapper.QuoteMapper
import com.tsci.learnlanguagewithquotes.domain.use_case.GetQuoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetQuoteUseCase(
        quoteMapper: QuoteMapper,
        quoteRepository: QuoteRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ) =
        GetQuoteUseCase(
            quoteMapper = quoteMapper,
            quoteRepository = quoteRepository,
            ioDispatcher = ioDispatcher
        )
}