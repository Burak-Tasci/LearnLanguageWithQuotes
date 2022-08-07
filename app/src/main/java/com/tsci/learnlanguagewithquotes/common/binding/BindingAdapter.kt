package com.tsci.learnlanguagewithquotes.common.binding

import android.widget.Button
import androidx.databinding.BindingAdapter


@BindingAdapter("buttonEnabled")
fun Button.buttonEnabled(isEnabled: Boolean) {
    this.alpha = if (isEnabled) 1f else 0.2f
    this.isEnabled = isEnabled
}