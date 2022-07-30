package com.tsci.learnlanguagewithquotes.core

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.tsci.learnlanguagewithquotes.R

/**
 * Created by Mehmet Peker on 13.07.2022.
 */
class BaseProgressDialog(context: Context) : Dialog(context) {

    init {
        setContentView(R.layout.dialog_progress)
        setCancelable(false)
        makeBackgroundTransparent()
    }

    private fun makeBackgroundTransparent() {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}