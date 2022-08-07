package com.tsci.learnlanguagewithquotes.ui.onboarding

import androidx.lifecycle.viewModelScope
import com.tsci.learnlanguagewithquotes.core.BaseViewModel
import com.tsci.learnlanguagewithquotes.data.local.preferences.PreferencesManager
import com.tsci.learnlanguagewithquotes.domain.use_case.onboarding.OnBoardingValidationParam
import com.tsci.learnlanguagewithquotes.domain.use_case.onboarding.OnBoardingValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val onBoardingValidationUseCase: OnBoardingValidationUseCase
) : BaseViewModel() {

    private val _nativeLanguage: MutableStateFlow<String> = MutableStateFlow("")
    val nativeLanguage = _nativeLanguage.asStateFlow()

    private val _targetLanguage: MutableStateFlow<String> = MutableStateFlow("")
    val targetLanguage = _targetLanguage.asStateFlow()

    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled = _isButtonEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            combine(nativeLanguage, targetLanguage) { native, target ->
                return@combine onBoardingValidationUseCase.invoke(
                    OnBoardingValidationParam(
                        native,
                        target
                    )
                )
            }.collect {
                _isButtonEnabled.value = it
            }
        }
    }


    fun setOnboardingPreference(hasOnBoarding: Boolean) {
        preferencesManager.setIsFirstOpening(hasOnBoarding)
    }
}