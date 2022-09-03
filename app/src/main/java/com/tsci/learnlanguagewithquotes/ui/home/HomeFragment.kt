package com.tsci.learnlanguagewithquotes.ui.home

import androidx.lifecycle.lifecycleScope
import com.tsci.learnlanguagewithquotes.R
import com.tsci.learnlanguagewithquotes.common.extension.clearChips
import com.tsci.learnlanguagewithquotes.common.extension.setChips
import com.tsci.learnlanguagewithquotes.core.BaseFragment
import com.tsci.learnlanguagewithquotes.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun layoutRes(): Int = R.layout.fragment_home

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView(): Unit = with(binding) {
        viewModel.getQuote()
        lifecycleScope.launch{
            viewModel.quote.collect{
                tvQuote.text = it.content
                tvOwner.text = it.owner.name
                cpTags.setChips(it.tags){}
            }
        }
    }

    override fun initListeners(): Unit = with(binding) {

        toolbar.ivRefresh.setOnClickListener {
            cpTags.clearChips()
            viewModel.getQuote()
        }
    }

}