package com.example.android.onboard.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.base.BaseFragment
import com.example.android.databinding.FragmentOnBoarding3Binding
import com.example.android.sign.view.SignUpActivity

class OnBoarding3Fragment : BaseFragment<FragmentOnBoarding3Binding>()
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener()
        {
            Intent(requireActivity(), SignUpActivity::class.java).run()
            {
                startActivity(this)
            }
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentOnBoarding3Binding
    {
        return FragmentOnBoarding3Binding.inflate(inflater, container, false)
    }
}