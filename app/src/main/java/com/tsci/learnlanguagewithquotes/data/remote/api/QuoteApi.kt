package com.tsci.learnlanguagewithquotes.data.remote.api

import com.tsci.learnlanguagewithquotes.data.remote.api.Route.RANDOM_QUOTE
import com.tsci.learnlanguagewithquotes.data.remote.model.QuoteEntity
import retrofit2.http.GET


interface QuoteApi {

    @GET(RANDOM_QUOTE)
    fun getQuote(): QuoteEntity
}

object Route{
    const val RANDOM_QUOTE = "/quotes/random/"
}