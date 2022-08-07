package com.tsci.learnlanguagewithquotes.core

import com.tsci.learnlanguagewithquotes.common.network.Result
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response

abstract class BaseRepositoryImpl {

    protected fun <T : Any> createCall(call: suspend () -> Response<T>): Flow<Result<T>> =
        callbackFlow {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    trySend(Result.Success(body))
                else
                    trySend(Result.Error(NullPointerException("Data null")))
            } else{
                trySend(Result.Error(Exception(" ${response.code()} ${response.message()}")))
            }
            this.awaitClose {
                this.cancel()
            }
        }
}
