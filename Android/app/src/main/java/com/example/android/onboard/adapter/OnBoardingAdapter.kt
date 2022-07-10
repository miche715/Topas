package com.example.android.onboard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android.onboard.view.OnBoarding1Fragment
import com.example.android.onboard.view.OnBoarding2Fragment
import com.example.android.onboard.view.OnBoarding3Fragment

class OnBoardingAdapter(fragmentActivity: FragmentActivity, private val onBoardingPageLength: Int) : FragmentStateAdapter(fragmentActivity)
{
    override fun createFragment(position: Int): Fragment
    {
        return when(position)
        {
            0 -> OnBoarding1Fragment()
            1 -> OnBoarding2Fragment()
            else -> OnBoarding3Fragment()
        }
    }

    override fun getItemCount() = onBoardingPageLength
}