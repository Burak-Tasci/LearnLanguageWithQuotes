package com.tsci.learnlanguagewithquotes.core

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.lifecycleScope
import com.tsci.learnlanguagewithquotes.common.dialog.ProgressDialog
import com.tsci.learnlanguagewithquotes.common.extension.toast
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

    private val progressDialog by lazy {
        ProgressDialog(fragmentContext)
    }
    protected lateinit var fragmentContext: Context

    // Binding
    protected lateinit var binding: T
        private set

    //ViewModel
    protected lateinit var viewModel: V
        private set

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun viewModelClass(): Class<V>

    protected abstract fun initView()

    protected open fun observeEvents() {}

    protected open fun initListeners() {}


    protected fun onDialogVisibilityChange(visibility: Boolean) {
        if (visibility) {
            progressDialog.show()
        } else {
            progressDialog.dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(fragmentContext),
            layoutRes(),
            container,
            false
        )
        viewModel = createViewModelLazy(viewModelClass().kotlin,{
            viewModelStore
        }).value
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCollectors()
        initView()
        initListeners()
        observeEvents()
    }

    private fun initCollectors() {
        lifecycleScope.launchWhenStarted {
            launch {
                viewModel.isLoading.collect(::handleLoadingState)
            }
            launch {
                viewModel.toastMessageState.collect(::handleToastMessage)
            }
        }
    }

    protected fun handleLoadingState(isLoading: Boolean) {
        onDialogVisibilityChange(isLoading)
    }

    protected fun handleToastMessage(message: String) {
        if (message.isNotEmpty()) {
            toast(message)
        }
    }
}
