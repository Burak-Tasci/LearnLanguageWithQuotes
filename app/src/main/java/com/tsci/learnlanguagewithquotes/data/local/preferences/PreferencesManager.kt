package com.tsci.learnlanguagewithquotes.data.local.preferences

import android.content.Context
import com.tsci.learnlanguagewithquotes.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesManager @Inject constructor(
    @ApplicationContext context: Context

) {
    private val preferencesManager =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    private val editor = preferencesManager.edit()

    fun getIsFirstOpening(): Boolean = preferencesManager.getBoolean(KEY_IS_FIRST_LOGIN, true)
    fun setIsFirstOpening(hasOnboarding: Boolean) = editor.putBoolean(KEY_IS_FIRST_LOGIN, hasOnboarding).apply()

    fun getNativeLanguage() = preferencesManager.getString(KEY_NATIVE, "")
    fun setNativeLanguage(nativeLanguage: String) = editor.putString(KEY_NATIVE, nativeLanguage).apply()

    fun getTargetLanguage() = preferencesManager.getString(KEY_TARGET, "")
    fun setTargetLanguage(targetLanguage: String) = editor.putString(KEY_TARGET, targetLanguage).apply()

    

    companion object{
        private const val KEY_IS_FIRST_LOGIN =  "isFirstLogin"
        private const val KEY_NATIVE = "native"
        private const val KEY_TARGET = "target"
    }
}