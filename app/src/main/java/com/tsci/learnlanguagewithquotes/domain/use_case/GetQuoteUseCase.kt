package com.tsci.learnlanguagewithquotes.domain.use_case

import com.tsci.learnlanguagewithquotes.common.network.Response
import com.tsci.learnlanguagewithquotes.core.BaseUseCase
import com.tsci.learnlanguagewithquotes.data.repository.QuoteRepository
import com.tsci.learnlanguagewithquotes.di.IoDispatcher
import com.tsci.learnlanguagewithquotes.domain.mapper.QuoteMapper
import com.tsci.learnlanguagewithquotes.domain.model.QuoteUiModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class GetQuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val quoteMapper: QuoteMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseUseCase<Unit, Flow<Response<QuoteUiModel>>>() {
    override suspend fun invoke(param: Unit): Flow<Response<QuoteUiModel>> = callbackFlow{

        withContext(ioDispatcher){
            val quoteData = quoteRepository.getQuote()
            quoteData.collect{ response ->
                response.onSuccess { quoteEntity ->
                    if (quoteEntity != null)
                        trySend(Response.Success(quoteMapper.map(quoteEntity)))
                    else
                        trySend(Response.Error(NullPointerException("Network call returned null.")))
                }.onError {

                }
            }
        }

        this.awaitClose {
            this.cancel()
        }
    }
}