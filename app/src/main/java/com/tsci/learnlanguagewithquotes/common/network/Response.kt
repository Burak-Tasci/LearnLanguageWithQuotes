package com.tsci.learnlanguagewithquotes.common.network

/**
 *Created by Mert Melih Aytemur on 1.07.2022.
 */
sealed class Response<out T> {

    object Loading : Response<Nothing>()

    data class Success<out T>(val data: T?) : Response<T>()

    data class Error<out T>(val exception: Exception) : Response<T>()

    object Empty : Response<Nothing>()

    fun onSuccess(successHandler: (T?) -> Unit): Response<T> = this.also {
        if (this is Success) {
            successHandler(data)
        }
    }

    fun onError(errorHandler: (Error<out T>) -> Unit): Response<T> = this.also {
        if (this is Error) {
            errorHandler(this)
        }
    }

    fun onLoading(loadingHandler: () -> Unit): Response<T> = this.also {
        if (this is Loading) {
            loadingHandler()
        }
    }
}