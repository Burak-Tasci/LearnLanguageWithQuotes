package com.tsci.learnlanguagewithquotes.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel(){
    var isLoading = MutableStateFlow(false)
        protected set

    var toastMessageState = MutableStateFlow("")
        protected set
}
