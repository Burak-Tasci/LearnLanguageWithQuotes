package com.tsci.learnlanguagewithquotes.ui.home

import androidx.lifecycle.viewModelScope
import com.tsci.learnlanguagewithquotes.common.network.Result
import com.tsci.learnlanguagewithquotes.core.BaseViewModel
import com.tsci.learnlanguagewithquotes.domain.model.QuoteUiModel
import com.tsci.learnlanguagewithquotes.domain.use_case.quote.GetQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getQuoteUseCase: GetQuoteUseCase
) : BaseViewModel() {

    private val _quote: MutableStateFlow<QuoteUiModel> = MutableStateFlow(QuoteUiModel())
    val quote = _quote.asStateFlow()

    private val _targetLanguageCode: MutableStateFlow<String> = MutableStateFlow("es")
    val targetLanguageCode = _targetLanguageCode.asStateFlow()

    fun getQuote() {
        viewModelScope.launch {
            getQuoteUseCase.invoke(targetLanguageCode.value).collect { getQuoteResponse ->
                handleGetQuoteResponse(getQuoteResponse)
            }
        }
    }

    private fun handleGetQuoteResponse(quoteResponse: Result<QuoteUiModel>) {
        quoteResponse.onSuccess { quote ->
            isLoading.value = false
            _quote.value = quote!!
        }.onError { errorResponse ->
            isLoading.value = false
            errorResponse.exception.message?.let { errorMessage ->
                toastMessageState.value = errorMessage
            }
        }.onLoading {
            isLoading.value = true
        }
    }
}