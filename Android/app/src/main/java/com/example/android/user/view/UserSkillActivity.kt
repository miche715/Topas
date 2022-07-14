package com.example.android.user.view

import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.net.toUri
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.contact.view.ContactActivity
import com.example.android.databinding.ActivityUserSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.android.base.BaseApplication.Companion.currentUser
import com.example.android.databinding.ActivityUserSkillBinding
import com.example.android.utility.LoadingDialog
import com.example.android.user.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar

@AndroidEntryPoint
class UserSkillActivity : BaseActivity<ActivityUserSkillBinding>(R.layout.activity_user_skill)
{
    private val userViewModel: UserViewModel by viewModels()

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)
    }
}