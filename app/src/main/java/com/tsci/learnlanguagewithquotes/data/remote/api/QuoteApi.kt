package com.tsci.learnlanguagewithquotes.data.remote.api

import com.tsci.learnlanguagewithquotes.data.remote.api.Route.LANGUAGE_CODE_QUERY
import com.tsci.learnlanguagewithquotes.data.remote.api.Route.RANDOM_QUOTE
import com.tsci.learnlanguagewithquotes.data.remote.model.QuoteEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface QuoteApi {

    @GET(RANDOM_QUOTE)
    suspend fun getQuote(@Query(LANGUAGE_CODE_QUERY) languageCode: String): Response<QuoteEntity>
}

object Route{
    const val RANDOM_QUOTE = "/quotes/random/"
    const val LANGUAGE_CODE_QUERY = "language_code"
}