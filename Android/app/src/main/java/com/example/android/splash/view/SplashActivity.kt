package com.example.android.splash.view

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivitySplashBinding
import com.example.android.splash.adapter.SplashAdapter

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>({ ActivitySplashBinding.inflate(it) })
{
    private val splashPageLength = 3

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding.viewPager.adapter = SplashAdapter(this, splashPageLength)
        binding.viewPager.currentItem = 0
        binding.viewPager.offscreenPageLimit = splashPageLength

        binding.indicator.setViewPager(binding.viewPager)
        binding.indicator.createIndicators(splashPageLength, 0)
    }
}



