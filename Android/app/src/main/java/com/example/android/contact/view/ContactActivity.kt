package com.example.android.contact.view

import android.content.Intent
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.base.BaseApplication.Companion.currentUser
import com.example.android.databinding.ActivityContactBinding
import com.example.android.user.view.UserSettingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactActivity : BaseActivity<ActivityContactBinding>(R.layout.activity_contact)
{
    override fun onInitialize()
    {
        binding.button.setOnClickListener()
        {
            Intent(this@ContactActivity, UserSettingActivity::class.java).run()
            {
                startActivity(this)
                finish()
            }
        }
    }
}
