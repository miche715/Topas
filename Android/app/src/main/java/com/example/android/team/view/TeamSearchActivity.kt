package com.example.android.team.view

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityTeamSearchBinding
import com.example.android.team.adapter.TeamSearchAdapter
import com.example.android.team.adapter.TeamSearchSkillAdapter
import com.example.android.team.doamin.Team
import com.example.android.team.viewmodel.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamSearchActivity : BaseActivity<ActivityTeamSearchBinding>(R.layout.activity_team_search)
{
    private val teamViewModel: TeamViewModel by viewModels()

    private val teamSearchSkillAdapter: TeamSearchSkillAdapter by lazy { TeamSearchSkillAdapter(this@TeamSearchActivity) }
    private val teamSearchAdapter: TeamSearchAdapter by lazy { TeamSearchAdapter(this@TeamSearchActivity) }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.searchSkillRecyclerView.adapter = teamSearchSkillAdapter
        binding.searchSkillRecyclerView.layoutManager = LinearLayoutManager(this@TeamSearchActivity)

        binding.teamRecyclerView.adapter = teamSearchAdapter
        binding.teamRecyclerView.layoutManager = LinearLayoutManager(this@TeamSearchActivity)

        binding.teamViewModel = teamViewModel
        binding.teamSearchActivity = this@TeamSearchActivity

        teamViewModel.searchTeamRequireSkillResult.observe(this@TeamSearchActivity)
        {
            teamSearchSkillAdapter.setSearchSkillList(it)
        }

        teamViewModel.loadTeamBySkillResult.observe(this@TeamSearchActivity)
        {
            teamSearchAdapter.addTeamList(it)
        }
    }

    fun searchSkill()
    {
        teamViewModel.searchSkill(binding.skillEditText.text.toString())
    }

    fun selectSkill(skill: String, view: View)
    {
        hideKeyBoard(view.windowToken)
        binding.skillEditText.text = null
        teamSearchAdapter.clearTeamList()
        loadTeam(skill)
    }

    private fun loadTeam(skill: String)
    {
        teamViewModel.loadTeamBySkillList(skill)
    }

    fun onClickTeam(team: Team)
    {
        println(team)
        Intent(this@TeamSearchActivity, TeamDetailActivity::class.java).run()
        {
            putExtra("team", team)
            startActivity(this)
        }
    }
}