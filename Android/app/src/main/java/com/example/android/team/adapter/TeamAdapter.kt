package com.example.android.team.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemTeamBinding
import com.example.android.team.doamin.Team
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class TeamAdapter(private val view: Any) : RecyclerView.Adapter<TeamAdapter.ViewHolder>()  // 멤버들의 목록을 표시하는 어댑터, 일반과 검색 둘 다 사용
{
    private var teamList = mutableListOf<Team>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(teamList[position])
    }

    override fun getItemCount(): Int = teamList.size

    inner class ViewHolder(private val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(team: Team)
        {
            val teamSkillAdapter = TeamSkillAdapter()
            val flexBoxLayoutManager = if(view is Fragment)
            {
                FlexboxLayoutManager(view.context)
            }
            else
            {
                FlexboxLayoutManager(view as Activity)
            }
            flexBoxLayoutManager.justifyContent = JustifyContent.CENTER

            binding.team = team
            binding.teamRequireSkillRecyclerView.apply()
            {
                adapter = teamSkillAdapter
                layoutManager = flexBoxLayoutManager
            }
            teamSkillAdapter.setTeamRequireSkillList(team.skill!!.toMutableList())
        }
    }

    fun addTeamList(teamList: MutableList<Team>)
    {
        this.teamList.addAll(teamList)
        notifyDataSetChanged()
    }

    fun clearMemberList()
    {
        this.teamList = mutableListOf()
    }
}