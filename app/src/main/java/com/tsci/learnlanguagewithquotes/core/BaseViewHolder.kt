package com.tsci.learnlanguagewithquotes.core

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: T)
}
