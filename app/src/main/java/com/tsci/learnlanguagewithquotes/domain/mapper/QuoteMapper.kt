package com.tsci.learnlanguagewithquotes.domain.mapper

import com.tsci.learnlanguagewithquotes.data.remote.model.OwnerEntity
import com.tsci.learnlanguagewithquotes.data.remote.model.QuoteEntity
import com.tsci.learnlanguagewithquotes.domain.model.QuoteUiModel
import javax.inject.Inject

class QuoteMapper @Inject constructor(
    private val ownerMapper: OwnerMapper
) {
    fun map(quoteEntity: QuoteEntity): QuoteUiModel = QuoteUiModel(
        language_code = quoteEntity.languageCode ?: "",
        content = quoteEntity.content ?: "",
        owner = ownerMapper.map(quoteEntity.owner ?: OwnerEntity()),
        tags = (quoteEntity.tags ?: emptyList()) as List<String>
    )

}
