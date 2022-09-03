package com.tsci.learnlanguagewithquotes.ui.splash

import com.tsci.learnlanguagewithquotes.core.BaseViewModel
import com.tsci.learnlanguagewithquotes.data.local.preferences.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) :BaseViewModel(){

    fun getIsFirstOpening() = preferencesManager.getIsFirstOpening()
}