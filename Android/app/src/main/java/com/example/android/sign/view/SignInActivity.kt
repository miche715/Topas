package com.example.android.sign.view

import android.os.Bundle
import androidx.activity.viewModels
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivitySignInBinding
import com.example.android.sign.viewmodel.SignViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>({ ActivitySignInBinding.inflate(it) })
{
    private val signViewModel: SignViewModel by viewModels()

    private val signLoadingDialog: SignLoadingDialog by lazy { SignLoadingDialog(this@SignInActivity) }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolBar)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)


    }
}