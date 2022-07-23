package com.example.android.team.view

import android.content.Intent
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.contact.view.ContactActivity
import com.example.android.databinding.ActivityTeamModifyBinding
import com.example.android.team.adapter.TeamCreateSearchSkillAdapter
import com.example.android.team.adapter.TeamCreateRequireSkillAdapter
import com.example.android.team.adapter.TeamModifyRequireSkillAdapter
import com.example.android.team.adapter.TeamModifySearchSkillAdapter
import com.example.android.team.doamin.Team
import com.example.android.team.viewmodel.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamModifyActivity : BaseActivity<ActivityTeamModifyBinding>(R.layout.activity_team_modify)
{
    private val teamViewModel: TeamViewModel by viewModels()

    private val teamModifySearchSkillAdapter: TeamModifySearchSkillAdapter by lazy { TeamModifySearchSkillAdapter(this@TeamModifyActivity) }
    private val teamModifyRequireSkillAdapter: TeamModifyRequireSkillAdapter by lazy { TeamModifyRequireSkillAdapter(this@TeamModifyActivity) }

    val team: Team by lazy { intent.getSerializableExtra("team") as Team }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.searchRequireSkillRecyclerView.adapter = teamModifySearchSkillAdapter
        binding.searchRequireSkillRecyclerView.layoutManager = LinearLayoutManager(this@TeamModifyActivity)

        binding.selectRequireSkillRecyclerView.adapter = teamModifyRequireSkillAdapter
        binding.selectRequireSkillRecyclerView.layoutManager = GridLayoutManager(this@TeamModifyActivity, 3)

        binding.teamViewModel = teamViewModel
        binding.teamModifyActivity = this@TeamModifyActivity
        binding.team = team

        team.skill!!.forEach()
        {
            selectSkill(it)
        }

        teamViewModel.searchTeamRequireSkillResult.observe(this@TeamModifyActivity)
        {
            teamModifySearchSkillAdapter.setSearchSkillList(it)
        }

        teamViewModel.selectedTeamRequireSkillResult.observe(this@TeamModifyActivity)
        {
            teamModifyRequireSkillAdapter.setSelectRequireSkillList(it)
        }

        teamViewModel.modifyTeamResult.observe(this@TeamModifyActivity)
        {
            if(it)
            {
                Intent(this@TeamModifyActivity, ContactActivity::class.java).run()
                {
                    startActivity(this)

                    finish()
                }
            }
        }
    }

    fun searchSkill()
    {
        teamViewModel.searchSkill(binding.skillEditText.text.toString())
    }

    fun selectSkill(skill: String)
    {
        teamViewModel.updateRequireSkill(skill)
        binding.skillEditText.text = null
    }

    fun modifyTeam()
    {
        teamViewModel.modifyTeam(binding.titleEditText.text.toString(), binding.explanationEditText.text.toString(), team.teamDocumentId!!)
    }
}