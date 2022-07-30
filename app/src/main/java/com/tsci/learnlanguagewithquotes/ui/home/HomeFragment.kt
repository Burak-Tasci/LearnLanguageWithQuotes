package com.tsci.learnlanguagewithquotes.ui.home

import com.appcent.learnlanguagewithquotes.R
import com.appcent.learnlanguagewithquotes.databinding.FragmentHomeBinding
import com.tsci.learnlanguagewithquotes.core.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>() {

    override fun layoutRes(): Int = R.layout.fragment_home

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {
        TODO("Not yet implemented")
    }
}