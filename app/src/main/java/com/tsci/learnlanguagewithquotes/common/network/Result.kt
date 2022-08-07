package com.tsci.learnlanguagewithquotes.common.network

/**
 *Created by Mert Melih Aytemur on 1.07.2022.
 */
sealed class Result<out T> {

    object Loading : Result<Nothing>()

    data class Success<out T>(val data: T?) : Result<T>()

    data class Error<out T>(val exception: Exception) : Result<T>()

    object Empty : Result<Nothing>()

    fun onSuccess(successHandler: (T?) -> Unit): Result<T> = this.also {
        if (this is Success) {
            successHandler(data)
        }
    }

    fun onError(errorHandler: (Error<out T>) -> Unit): Result<T> = this.also {
        if (this is Error) {
            errorHandler(this)
        }
    }

    fun onLoading(loadingHandler: () -> Unit): Result<T> = this.also {
        if (this is Loading) {
            loadingHandler()
        }
    }
}