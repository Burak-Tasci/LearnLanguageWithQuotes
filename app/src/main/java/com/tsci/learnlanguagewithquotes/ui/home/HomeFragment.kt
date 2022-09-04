package com.tsci.learnlanguagewithquotes.ui.home

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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
        lifecycleScope.launch{
            viewModel.quote.collect{ quote ->
                tvQuote.text = quote.content
                tvOwner.text = quote.owner.name
                cpTags.setChips(quote.tags){ position ->
                    quote.tags[position].also { textToTranslate ->
                        val action = HomeFragmentDirections.globalTranslateDialog(textToTranslate)
                        findNavController().navigate(action)
                    }
                }
                tvQuote.setOnLongClickListener {
                    val action = HomeFragmentDirections.globalTranslateDialog(quote.content)
                    findNavController().navigate(action)
                    true
                }
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // todo refactor target language check
        viewModel.refreshTargetLanguage()
        viewModel.getQuote()
    }

    override fun initListeners(): Unit = with(binding) {

        toolbar.ivRefresh.setOnClickListener {
            cpTags.clearChips()
            viewModel.getQuote()
        }
        toolbar.ivSettings.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.toOnBoardingFragment()
            )
        }
    }

}