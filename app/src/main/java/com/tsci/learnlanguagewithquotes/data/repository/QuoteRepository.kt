package com.tsci.learnlanguagewithquotes.data.repository

import com.tsci.learnlanguagewithquotes.common.network.Result
import com.tsci.learnlanguagewithquotes.core.BaseRepositoryImpl
import com.tsci.learnlanguagewithquotes.data.remote.api.QuoteApi
import com.tsci.learnlanguagewithquotes.data.remote.model.QuoteEntity
import javax.inject.Inject
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface QuoteRepository {
    fun getQuote(languageCode: String): Flow<Result<QuoteEntity>>
}

class QuoteRepositoryImpl @Inject constructor(
    private val api: QuoteApi
) : QuoteRepository, BaseRepositoryImpl() {

    override fun getQuote(languageCode: String): Flow<Result<QuoteEntity>> = callbackFlow {
        createCall {
            api.getQuote(languageCode)
        }.collect{ response ->
            response.onSuccess {
                trySend(Result.Success(it))
            }.onError {
                trySend(Result.Error(it.exception))
            }
        }

        this.awaitClose {
            this.cancel()
        }
    }
}