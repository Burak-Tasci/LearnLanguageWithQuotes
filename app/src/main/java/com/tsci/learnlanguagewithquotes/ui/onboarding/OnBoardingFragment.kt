package com.tsci.learnlanguagewithquotes.ui.onboarding

import com.tsci.learnlanguagewithquotes.R
import com.tsci.learnlanguagewithquotes.core.BaseFragment
import com.tsci.learnlanguagewithquotes.databinding.FragmentOnBoardingBinding


class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, OnBoardingViewModel>() {

    override fun layoutRes(): Int = R.layout.fragment_on_boarding

    override fun initView() {

    }
    override fun viewModelClass(): Class<OnBoardingViewModel> = OnBoardingViewModel::class.java

}