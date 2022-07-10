package com.example.android.onboard.view

import android.os.Bundle
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityOnBoardingBinding
import com.example.android.onboard.adapter.OnBoardingAdapter

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>({ ActivityOnBoardingBinding.inflate(it) })
{
    private val onBoardingPageLength = 3

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding.viewPager.adapter = OnBoardingAdapter(this, onBoardingPageLength)
        binding.viewPager.currentItem = 0
        binding.viewPager.offscreenPageLimit = onBoardingPageLength

        binding.indicator.setViewPager(binding.viewPager)
        binding.indicator.createIndicators(onBoardingPageLength, 0)
    }
}