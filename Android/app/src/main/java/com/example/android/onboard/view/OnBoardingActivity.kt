package com.example.android.onboard.view

import android.content.Intent
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.base.BaseApplication.Companion.sharedPreferences
import com.example.android.databinding.ActivityOnBoardingBinding
import com.example.android.onboard.adapter.OnBoardingAdapter
import com.example.android.sign.view.SignInActivity

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>(R.layout.activity_on_boarding)
{
    private val onBoardingPageLength = 3

    override fun onInitialize()
    {
        if(sharedPreferences.getBoolean("isOnBoarded", false))  // 사용자가 이미 온보딩 화면을 봤는지 검사
        {
            Intent(this, SignInActivity::class.java).run()
            {
                startActivity(this)

                finish()
            }
        }

        binding.viewPager.adapter = OnBoardingAdapter(this@OnBoardingActivity, onBoardingPageLength)
        binding.viewPager.currentItem = 0
        binding.viewPager.offscreenPageLimit = onBoardingPageLength

        binding.indicator.setViewPager(binding.viewPager)
        binding.indicator.createIndicators(onBoardingPageLength, 0)
    }
}