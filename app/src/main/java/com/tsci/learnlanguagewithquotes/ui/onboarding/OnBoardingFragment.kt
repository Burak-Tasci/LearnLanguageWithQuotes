package com.tsci.learnlanguagewithquotes.ui.onboarding

import com.appcent.learnlanguagewithquotes.R
import com.appcent.learnlanguagewithquotes.databinding.FragmentOnBoardingBinding
import com.tsci.learnlanguagewithquotes.core.BaseFragment


class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, Nothing>() {

    override fun layoutRes(): Int = R.layout.fragment_on_boarding

    override fun initView() {
        TODO("Not yet implemented")
    }
    override fun viewModelClass(): Class<Nothing> = Nothing::class.java

}