package com.tsci.learnlanguagewithquotes.common.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.tsci.learnlanguagewithquotes.R


class ProgressDialog(context: Context) : Dialog(context) {

    init {
        setContentView(R.layout.dialog_progress)
        setCancelable(false)
        makeBackgroundTransparent()
    }

    private fun makeBackgroundTransparent() {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}