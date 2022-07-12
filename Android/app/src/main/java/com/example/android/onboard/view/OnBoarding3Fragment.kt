package com.example.android.onboard.view

import android.content.Intent
import com.example.android.R
import com.example.android.base.BaseApplication.Companion.sharedPreferences
import com.example.android.base.BaseFragment
import com.example.android.databinding.FragmentOnBoarding3Binding
import com.example.android.sign.view.SignInActivity
import com.example.android.sign.view.SignUpActivity

class OnBoarding3Fragment : BaseFragment<FragmentOnBoarding3Binding>(R.layout.fragment_on_boarding3)
{
    override fun onInitialize()
    {
        binding!!.onBoarding3Fragment = this
    }

    fun signUp()
    {
        sharedPreferences.edit().putBoolean("isOnBoarded", true).commit()  // 온보딩 화면 다 봤으니까, 다시 않나오게 저장

        Intent(activity, SignUpActivity::class.java).run()
        {
            startActivity(this)
        }
    }

    fun signIn()
    {
        sharedPreferences.edit().putBoolean("isOnBoarded", true).commit()  // 온보딩 화면 다 봤으니까, 다시 않나오게 저장

        Intent(activity, SignInActivity::class.java).run()
        {
            startActivity(this)
        }
    }
}