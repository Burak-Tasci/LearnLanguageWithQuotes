package com.tsci.learnlanguagewithquotes.data.remote.model

import com.google.gson.annotations.SerializedName

data class QuoteEntity(
    val id: Int? = null,
    val content: String? = null,
    @SerializedName("language_code")
    val languageCode: String? = null,
    @SerializedName("originator")
    val owner: OwnerEntity? = null,
    val tags: List<String?>? = null,
    val url: String? = null
)