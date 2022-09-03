package com.tsci.learnlanguagewithquotes.ui.splash

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tsci.learnlanguagewithquotes.R
import com.tsci.learnlanguagewithquotes.core.BaseFragment
import com.tsci.learnlanguagewithquotes.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun viewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun initView() {
        lifecycleScope.launch{
            delay(DELAY)
            val action = if (viewModel.getIsFirstOpening()) {
                SplashFragmentDirections.toOnBoardingFragment()
            } else {
                SplashFragmentDirections.toHomeFragment()
            }
            findNavController().navigate(action)

        }
    }

    companion object{
        private const val DELAY = 2000L
    }
}