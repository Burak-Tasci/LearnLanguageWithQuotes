package com.tsci.learnlanguagewithquotes.ui.onboarding

import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.tsci.learnlanguagewithquotes.R
import com.tsci.learnlanguagewithquotes.core.BaseFragment
import com.tsci.learnlanguagewithquotes.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, OnBoardingViewModel>() {

    override fun viewModelClass(): Class<OnBoardingViewModel> = OnBoardingViewModel::class.java
    override fun layoutRes(): Int = R.layout.fragment_on_boarding

    override fun initView(): Unit = with(binding) {
        this.viewModel = this@OnBoardingFragment.viewModel
        executePendingBindings()

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_language,
            resources.getStringArray(R.array.languages)
        )
        actvNative.setAdapter(adapter)
        actvTarget.setAdapter(adapter)

        viewModel?.setOnboardingPreference(false)
    }


    override fun initListeners() = with(binding) {
        btnSubmit.setOnClickListener {
            if (findNavController().previousBackStackEntry?.destination?.id == R.id.homeFragment){
                findNavController().popBackStack()
            } else {
                findNavController().navigate(OnBoardingFragmentDirections.toHomeFragment())
            }
        }
    }
}