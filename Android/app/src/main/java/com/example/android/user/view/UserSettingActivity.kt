package com.example.android.user.view

import android.content.Intent
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.contact.view.ContactActivity
import com.example.android.databinding.ActivityUserSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.android.base.BaseApplication.Companion.currentUser
import com.example.android.user.viewmodel.UserViewModel

@AndroidEntryPoint
class UserSettingActivity : BaseActivity<ActivityUserSettingBinding>(R.layout.activity_user_setting)
{
    private val userViewModel: UserViewModel by viewModels()

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.currentUser = currentUser
        binding.userViewModel = userViewModel
        binding.userSettingActivity = this@UserSettingActivity
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean  // 여기서만 재정의
    {
        when(item.itemId)
        {
            android.R.id.home -> {
                Intent(this@UserSettingActivity, ContactActivity::class.java).run()
                {
                    startActivity(this)
                    finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}