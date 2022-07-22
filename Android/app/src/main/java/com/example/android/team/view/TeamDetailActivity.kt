package com.example.android.team.view

import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityTeamDetailBinding
import com.example.android.team.adapter.TeamDetailRequireSkillAdapter
import com.example.android.team.doamin.Team
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailActivity : BaseActivity<ActivityTeamDetailBinding>(R.layout.activity_team_detail)
{
    val team: Team by lazy { intent.getSerializableExtra("team") as Team }

    private val teamDetailRequireSkillAdapter: TeamDetailRequireSkillAdapter by lazy { TeamDetailRequireSkillAdapter(team.skill!!) }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.team = team
        binding.teamDetailActivity = this@TeamDetailActivity

        binding.skillRecyclerView.adapter = teamDetailRequireSkillAdapter
        binding.skillRecyclerView.layoutManager = GridLayoutManager(this@TeamDetailActivity, 3)
    }

    fun onContactClick()
    {
        println("함께 하기")
    }
}