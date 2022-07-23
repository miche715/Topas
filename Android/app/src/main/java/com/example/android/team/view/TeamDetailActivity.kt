package com.example.android.team.view

import android.content.Intent
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.base.BaseApplication.Companion.currentUser
import com.example.android.contact.view.ContactActivity
import com.example.android.databinding.ActivityTeamDetailBinding
import com.example.android.team.adapter.TeamDetailRequireSkillAdapter
import com.example.android.team.doamin.Team
import com.example.android.team.viewmodel.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailActivity : BaseActivity<ActivityTeamDetailBinding>(R.layout.activity_team_detail)
{
    private val teamViewModel: TeamViewModel by viewModels()

    private val team: Team by lazy { intent.getSerializableExtra("team") as Team }

    private val teamDetailRequireSkillAdapter: TeamDetailRequireSkillAdapter by lazy { TeamDetailRequireSkillAdapter(team.skill!!) }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.team = team
        binding.teamDetailActivity = this@TeamDetailActivity
        binding.currentUser = currentUser

        binding.skillRecyclerView.adapter = teamDetailRequireSkillAdapter
        binding.skillRecyclerView.layoutManager = GridLayoutManager(this@TeamDetailActivity, 3)

        teamViewModel.deleteTeamResult.observe(this@TeamDetailActivity)
        {
            if(it)
            {
                Intent(this@TeamDetailActivity, ContactActivity::class.java).run()
                {
                    startActivity(this)

                    finish()
                }
            }
        }
    }

    fun onModifyClick(team: Team)
    {
        Intent(this@TeamDetailActivity, TeamModifyActivity::class.java).run()
        {
            putExtra("team", team)
            startActivity(this)
        }
    }

    fun onDeleteClick(team: Team)
    {
        with(AlertDialog.Builder(this))
        {
            this.setMessage("팀을 삭제 하시겠습니까?")
            this.setPositiveButton("확인") { _, _ -> teamViewModel.deleteTeam(team) }
            this.setNegativeButton("취소") { _, _ -> }
        }.show()
    }

    fun onContactClick()
    {
        println("함께 하기")
    }
}