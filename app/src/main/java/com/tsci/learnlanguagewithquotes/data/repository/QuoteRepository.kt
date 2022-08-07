package com.tsci.learnlanguagewithquotes.data.repository

import com.tsci.learnlanguagewithquotes.common.network.Response
import com.tsci.learnlanguagewithquotes.data.remote.api.QuoteApi
import com.tsci.learnlanguagewithquotes.data.remote.model.QuoteEntity
import javax.inject.Inject
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface QuoteRepository {
    fun getQuote(): Flow<Response<QuoteEntity>>
}

class QuoteRepositoryImpl @Inject constructor(
    private val api: QuoteApi
) : QuoteRepository {

    override fun getQuote(): Flow<Response<QuoteEntity>> = callbackFlow{

        val quote = api.getQuote()
        quote.collect { response ->
            response.onSuccess { quote ->
                if (quote != null) trySend(Response.Success(quote))
                else trySend(Response.Error(Exception("Network call returned null.")))
            }.onError {
                trySend(Response.Error(it.exception))
            }
        }

        this.awaitClose {
            this.cancel()
        }
    }

}