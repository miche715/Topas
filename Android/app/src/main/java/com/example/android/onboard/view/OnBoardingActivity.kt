package com.example.android.onboard.view

import android.content.Intent
import android.os.Bundle
import com.example.android.base.BaseActivity
import com.example.android.base.BaseApplication
import com.example.android.databinding.ActivityOnBoardingBinding
import com.example.android.onboard.adapter.OnBoardingAdapter
import com.example.android.sign.view.SignUpActivity

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>({ ActivityOnBoardingBinding.inflate(it) })
{
    private val onBoardingPageLength = 3

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

//        if(BaseApplication.sharedPreferences.getBoolean("isOnBoarded", false))  // 사용자가 이미 온보딩 화면을 봤는지 검사
//        {
//            Intent(this, SignUpActivity::class.java).run()
//            {
//                startActivity(this)
//
//                finish()
//            }
//        }

        binding.viewPager.adapter = OnBoardingAdapter(this, onBoardingPageLength)
        binding.viewPager.currentItem = 0
        binding.viewPager.offscreenPageLimit = onBoardingPageLength

        binding.indicator.setViewPager(binding.viewPager)
        binding.indicator.createIndicators(onBoardingPageLength, 0)
    }
}