package com.example.android.team.view

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.base.BaseFragment
import com.example.android.databinding.FragmentTeamContactBinding
import com.example.android.team.adapter.TeamAdapter
import com.example.android.team.viewmodel.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamContactFragment : BaseFragment<FragmentTeamContactBinding>(R.layout.fragment_team_contact)
{
    private val teamViewModel: TeamViewModel by activityViewModels()

    private val teamAdapter: TeamAdapter by lazy { TeamAdapter(this@TeamContactFragment) }

    override fun onInitialize()
    {
        binding!!.teamContactFragment = this@TeamContactFragment

        binding!!.teamRecyclerView.adapter = teamAdapter
        binding!!.teamRecyclerView.layoutManager = LinearLayoutManager(this@TeamContactFragment.context)

        teamViewModel.initializeLoadTeamListQuery()
        loadTeam()

        binding!!.swipeRefreshLayout.setOnRefreshListener()  // 맨 위로 스크롤 하면 새로고침
        {
            teamAdapter.clearTeamList()  // 어댑터의 리스트를 비움
            teamViewModel.initializeLoadTeamListQuery()  // 쿼리를 초기로 돌림
            loadTeam()  // 리스트 로딩

            binding!!.swipeRefreshLayout.isRefreshing = false
        }

        binding!!.teamRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener()  // 리사이클러뷰의 스크롤 이벤트 감지
        {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
            {
                super.onScrolled(recyclerView, dx, dy)

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

    fun createTeam()
    {
        Intent(this@TeamContactFragment.context, TeamCreateActivity::class.java).run()
        {
            startActivity(this)
        }
    }

    private fun loadTeam()
    {
        teamViewModel.loadMemberList()
    }
}