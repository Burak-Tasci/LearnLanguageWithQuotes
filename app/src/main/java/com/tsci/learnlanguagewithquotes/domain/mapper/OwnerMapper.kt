package com.tsci.learnlanguagewithquotes.domain.mapper

import com.tsci.learnlanguagewithquotes.data.remote.model.OwnerEntity
import com.tsci.learnlanguagewithquotes.domain.model.OwnerUiModel
import javax.inject.Inject

class OwnerMapper @Inject constructor(

) {
    fun map(ownerEntity: OwnerEntity): OwnerUiModel = OwnerUiModel(
        name = ownerEntity.name ?: ""
    )
}