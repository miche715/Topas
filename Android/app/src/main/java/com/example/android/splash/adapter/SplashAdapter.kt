package com.example.android.splash.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android.splash.view.Splash1Fragment
import com.example.android.splash.view.Splash2Fragment
import com.example.android.splash.view.Splash3Fragment

class SplashAdapter(fragmentActivity: FragmentActivity, private val splashPageLength: Int) : FragmentStateAdapter(fragmentActivity)
{
    override fun createFragment(position: Int): Fragment
    {
        return when(position)
        {
            0 -> Splash1Fragment()
            1 -> Splash2Fragment()
            else -> Splash3Fragment()
        }
    }

    override fun getItemCount() = splashPageLength
}