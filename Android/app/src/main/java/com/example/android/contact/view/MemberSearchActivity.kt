package com.example.android.contact.view

import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityMemberSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberSearchActivity : BaseActivity<ActivityMemberSearchBinding>(R.layout.activity_member_search)
{
    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)
    }
}