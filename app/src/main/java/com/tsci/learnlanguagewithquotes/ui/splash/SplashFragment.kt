package com.tsci.learnlanguagewithquotes.ui.splash

import com.appcent.learnlanguagewithquotes.R
import com.appcent.learnlanguagewithquotes.databinding.FragmentSplashBinding
import com.tsci.learnlanguagewithquotes.core.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding, Nothing>() {

    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun viewModelClass(): Class<Nothing> = Nothing::class.java

    override fun initView() {
        TODO("Not yet implemented")
    }

}