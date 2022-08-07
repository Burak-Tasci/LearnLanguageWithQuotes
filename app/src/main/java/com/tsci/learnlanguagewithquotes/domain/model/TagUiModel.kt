package com.tsci.learnlanguagewithquotes.domain.model

data class TagUiModel(
    val owner: OwnerUiModel = OwnerUiModel(""),
    val content: String = "",
    val language_code: String = "",
    val tags: List<String> = emptyList()
)
