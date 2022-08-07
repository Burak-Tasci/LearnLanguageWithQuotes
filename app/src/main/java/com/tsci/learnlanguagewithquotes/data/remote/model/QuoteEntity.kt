package com.tsci.learnlanguagewithquotes.data.remote.model

data class QuoteEntity(
    val content: String,
    val id: Int,
    val language_code: String,
    val owner: OwnerEntity,
    val tags: List<String>,
    val url: String
)