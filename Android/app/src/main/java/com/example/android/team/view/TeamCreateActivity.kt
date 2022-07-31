package com.example.android.team.view

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityTeamCreateBinding
import com.example.android.team.adapter.TeamCreateSearchSkillAdapter
import com.example.android.team.adapter.TeamCreateRequireSkillAdapter
import com.example.android.team.viewmodel.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamCreateActivity : BaseActivity<ActivityTeamCreateBinding>(R.layout.activity_team_create)
{
    private val teamViewModel: TeamViewModel by viewModels()

    private val teamCreateSearchSkillAdapter: TeamCreateSearchSkillAdapter by lazy { TeamCreateSearchSkillAdapter(this@TeamCreateActivity) }
    private val teamCreateRequireSkillAdapter: TeamCreateRequireSkillAdapter by lazy { TeamCreateRequireSkillAdapter(this@TeamCreateActivity) }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.searchRequireSkillRecyclerView.adapter = teamCreateSearchSkillAdapter
        binding.searchRequireSkillRecyclerView.layoutManager = LinearLayoutManager(this@TeamCreateActivity)

        binding.selectRequireSkillRecyclerView.adapter = teamCreateRequireSkillAdapter
        binding.selectRequireSkillRecyclerView.layoutManager = GridLayoutManager(this@TeamCreateActivity, 3)

        binding.teamViewModel = teamViewModel
        binding.teamCreateActivity = this@TeamCreateActivity

        teamViewModel.searchTeamRequireSkillResult.observe(this@TeamCreateActivity)
        {
            teamCreateSearchSkillAdapter.setSearchSkillList(it)
        }

        teamViewModel.selectedTeamRequireSkillResult.observe(this@TeamCreateActivity)
        {
            teamCreateRequireSkillAdapter.setSelectRequireSkillList(it)
        }

        teamViewModel.createTeamResult.observe(this@TeamCreateActivity)
        {
            if(it)
            {
                finish()
            }
        }
    }

    fun onSkillEditTextChange()
    {
        teamViewModel.searchSkill(binding.skillEditText.text.toString())
    }

    fun onSkillTextViewOrRemoveSkillImageButtonClick(skill: String)
    {
        teamViewModel.updateRequireSkill(skill)
        binding.skillEditText.text = null
    }

    fun onCreateTeamButtonClick()
    {
        teamViewModel.createTeam(binding.titleEditText.text.toString(), binding.explanationEditText.text.toString())
    }
}