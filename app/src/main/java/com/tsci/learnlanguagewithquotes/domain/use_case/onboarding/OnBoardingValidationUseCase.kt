package com.tsci.learnlanguagewithquotes.domain.use_case.onboarding

import com.tsci.learnlanguagewithquotes.core.BaseUseCase
import javax.inject.Inject

class OnBoardingValidationUseCase @Inject constructor(

) : BaseUseCase<OnBoardingValidationParam, Boolean>() {
    override suspend fun invoke(param: OnBoardingValidationParam): Boolean =
        param.native.isNotEmpty() and param.target.isNotEmpty()
}

data class OnBoardingValidationParam(val native: String, val target: String)