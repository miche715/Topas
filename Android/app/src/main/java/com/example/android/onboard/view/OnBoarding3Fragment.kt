package com.example.android.onboard.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.base.BaseApplication.Companion.sharedPreferences
import com.example.android.base.BaseFragment
import com.example.android.databinding.FragmentOnBoarding3Binding
import com.example.android.sign.view.SignUpActivity

class OnBoarding3Fragment : BaseFragment<FragmentOnBoarding3Binding>()
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener()
        {
            sharedPreferences.edit().putBoolean("isOnBoarded", true).commit()  // 온보딩 화면 다 봤으니까, 다시 않나오게 저장

            Intent(activity, SignUpActivity::class.java).run()
            {
                startActivity(this)

                activity?.finish()
            }
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentOnBoarding3Binding
    {
        return FragmentOnBoarding3Binding.inflate(inflater, container, false)
    }
}