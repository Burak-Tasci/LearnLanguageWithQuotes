package com.tsci.learnlanguagewithquotes.common.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.tsci.learnlanguagewithquotes.data.local.preferences.PreferencesManager
import com.tsci.learnlanguagewithquotes.databinding.DialogTranslateBinding


class TranslateDialog: DialogFragment(){

    private val args by navArgs<TranslateDialogArgs>()
    private lateinit var binding : DialogTranslateBinding

    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogTranslateBinding.inflate(layoutInflater)
        preferencesManager = PreferencesManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val targetUrl = "${GOOGLE_TRANSLATE_URL}?" +
                "sl=${preferencesManager.getTargetLanguage()}&" +
                "tl=${preferencesManager.getNativeLanguage()}&" +
                "text=${args.translateText}&" +
                "op=translate"

        binding.wvTranslate.apply {
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    return true
                }
            }
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            loadUrl(targetUrl)
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.50).toInt()
        requireDialog().window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        requireDialog().window?.setLayout(height, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


    companion object{
        const val TAG = "TranslateDialog"
        private const val GOOGLE_TRANSLATE_URL = "https://translate.google.com/"
    }
}