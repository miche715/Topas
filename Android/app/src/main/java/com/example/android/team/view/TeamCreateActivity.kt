package com.example.android.team.view

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityTeamCreateBinding
import com.example.android.team.adapter.SearchRequireSkillAdapter
import com.example.android.team.adapter.SelectRequireSkillAdapter
import com.example.android.team.viewmodel.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamCreateActivity : BaseActivity<ActivityTeamCreateBinding>(R.layout.activity_team_create)
{
    private val teamViewModel: TeamViewModel by viewModels()

    private val searchRequireSkillAdapter: SearchRequireSkillAdapter by lazy { SearchRequireSkillAdapter(this@TeamCreateActivity) }
    private val selectRequireSkillAdapter: SelectRequireSkillAdapter by lazy { SelectRequireSkillAdapter(this@TeamCreateActivity) }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.searchRequireSkillRecyclerView.adapter = searchRequireSkillAdapter
        binding.searchRequireSkillRecyclerView.layoutManager = LinearLayoutManager(this@TeamCreateActivity)

        binding.selectRequireSkillRecyclerView.adapter = selectRequireSkillAdapter
        binding.selectRequireSkillRecyclerView.layoutManager = GridLayoutManager(this@TeamCreateActivity, 3)

        binding.teamViewModel = teamViewModel
        binding.teamCreateActivity = this@TeamCreateActivity

        teamViewModel.searchTeamRequireSkillResult.observe(this@TeamCreateActivity)
        {
            searchRequireSkillAdapter.setSearchSkillList(it)
        }

        teamViewModel.selectedTeamRequireSkillResult.observe(this@TeamCreateActivity)
        {
            selectRequireSkillAdapter.setSelectRequireSkillList(it)
        }

        teamViewModel.createTeamResult.observe(this@TeamCreateActivity)
        {
            if(it)
            {
                finish()
            }
        }
    }

    fun searchSkill()
    {
        teamViewModel.searchSkill(binding.skillEditText.text.toString())
    }

    fun selectSkill(skill: String)
    {
        teamViewModel.updateMySkill(skill)
        binding.skillEditText.text = null
    }

    fun createTeam()
    {
        teamViewModel.createTeam(binding.titleEditText.text.toString(), binding.explanationEditText.text.toString())
    }
}