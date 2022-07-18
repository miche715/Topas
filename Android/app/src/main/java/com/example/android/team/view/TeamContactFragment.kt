package com.example.android.team.view

import android.content.Intent
import com.example.android.R
import com.example.android.base.BaseFragment
import com.example.android.databinding.FragmentTeamContactBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamContactFragment : BaseFragment<FragmentTeamContactBinding>(R.layout.fragment_team_contact)
{
    override fun onInitialize()
    {
        binding!!.teamContactFragment = this@TeamContactFragment
    }

    fun createTeam()
    {
        Intent(this@TeamContactFragment.context, TeamCreateActivity::class.java).run()
        {
            startActivity(this)
        }
    }
}