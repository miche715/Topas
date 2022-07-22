package com.example.android.team.view

import android.content.Intent
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.base.BaseFragment
import com.example.android.databinding.FragmentTeamContactBinding
import com.example.android.team.adapter.TeamAdapter
import com.example.android.team.doamin.Team
import com.example.android.team.viewmodel.TeamViewModel
import com.example.android.utility.TeamViewMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamContactFragment : BaseFragment<FragmentTeamContactBinding>(R.layout.fragment_team_contact)
{
    private val teamViewModel: TeamViewModel by activityViewModels()

    private val teamAdapter: TeamAdapter by lazy { TeamAdapter(this@TeamContactFragment) }

    val items: Array<String> by lazy {resources.getStringArray(R.array.team)}
    private val teamViewModeSpinnerAdapter by lazy { ArrayAdapter(this@TeamContactFragment.context!!, android.R.layout.simple_dropdown_item_1line, items) }
    private var teamViewMode = TeamViewMode.All

    override fun onInitialize()
    {
        binding!!.teamContactFragment = this@TeamContactFragment

        binding!!.teamRecyclerView.adapter = teamAdapter
        binding!!.teamRecyclerView.layoutManager = LinearLayoutManager(this@TeamContactFragment.context)

        binding!!.teamViewModeSpinner.adapter = teamViewModeSpinnerAdapter

        binding!!.swipeRefreshLayout.setOnRefreshListener()  // 맨 위로 스크롤 하면 새로고침
        {
            loadAndInitializeTeam()

            binding!!.swipeRefreshLayout.isRefreshing = false
        }

        binding!!.teamRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener()  // 리사이클러뷰의 스크롤 이벤트 감지
        {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int)
            {
                super.onScrollStateChanged(recyclerView, newState)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter?.itemCount

                if(lastVisibleItemPosition + 1 == itemTotalCount)  // 리사이클러뷰가 현재 목록의 끝에 닿으면
                {
                    loadTeam()
                }
            }
        })

        teamViewModel.loadTeamListResult.observe(this@TeamContactFragment.activity!!)
        {
            teamAdapter.addTeamList(it)
        }
    }

    fun teamViewModeSelect()
    {
        when(binding!!.teamViewModeSpinner.selectedItemPosition)
        {
            0 -> teamViewMode = TeamViewMode.All
            1 -> teamViewMode = TeamViewMode.MY
        }
        loadAndInitializeTeam()
    }

    private fun loadAndInitializeTeam()
    {
        teamAdapter.clearTeamList()  // 어댑터의 리스트를 비움
        when(teamViewMode)
        {
            TeamViewMode.All -> {
                teamViewModel.initializeLoadTeamListQuery()
            }
            else -> {
                teamViewModel.initializeLoadMyTeamListQuery()
            }
        }
        loadTeam()
    }

    private fun loadTeam()
    {
        when(teamViewMode)
        {
            TeamViewMode.All -> {
                teamViewModel.loadTeamList()
            }
            else -> {
                teamViewModel.loadMyTeamList()
            }
        }
    }

    fun createTeam()
    {
        Intent(this@TeamContactFragment.context, TeamCreateActivity::class.java).run()
        {
            startActivity(this)
        }
    }

    fun onClickTeam(team: Team)
    {
        println(team)
        Intent(this@TeamContactFragment.context, TeamDetailActivity::class.java).run()
        {
            putExtra("team", team)
            startActivity(this)
        }
    }
}