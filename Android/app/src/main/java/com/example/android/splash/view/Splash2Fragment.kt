package com.example.android.splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.base.BaseFragment
import com.example.android.databinding.FragmentSplash2Binding

class Splash2Fragment : BaseFragment<FragmentSplash2Binding>()
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSplash2Binding
    {
        return FragmentSplash2Binding.inflate(inflater, container, false)
    }
}