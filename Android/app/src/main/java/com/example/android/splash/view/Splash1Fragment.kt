package com.example.android.splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.base.BaseFragment
import com.example.android.databinding.FragmentSplash1Binding

class Splash1Fragment : BaseFragment<FragmentSplash1Binding>()
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSplash1Binding
    {
        return FragmentSplash1Binding.inflate(inflater, container, false)
    }
}