package com.tsci.learnlanguagewithquotes.data.remote.model

data class QuoteEntity(
    val content: String? = null,
    val id: Int? = null,
    val language_code: String? = null,
    val owner: OwnerEntity? = null,
    val tags: List<String?>? = null,
    val url: String? = null
)