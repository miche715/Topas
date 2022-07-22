package com.example.android.team.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityTeamDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailActivity : BaseActivity<ActivityTeamDetailBinding>(R.layout.activity_team_detail)
{
    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)
    }
}