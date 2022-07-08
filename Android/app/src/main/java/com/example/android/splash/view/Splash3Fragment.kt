package com.example.android.splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.base.BaseFragment
import com.example.android.databinding.FragmentSplash3Binding

class Splash3Fragment : BaseFragment<FragmentSplash3Binding>()
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener()
        {
            
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSplash3Binding
    {
        return FragmentSplash3Binding.inflate(inflater, container, false)
    }
}