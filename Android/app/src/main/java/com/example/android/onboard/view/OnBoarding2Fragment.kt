package com.example.android.onboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.base.BaseFragment
import com.example.android.databinding.FragmentOnBoarding2Binding

class OnBoarding2Fragment : BaseFragment<FragmentOnBoarding2Binding>()
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentOnBoarding2Binding
    {
        return FragmentOnBoarding2Binding.inflate(inflater, container, false)
    }
}