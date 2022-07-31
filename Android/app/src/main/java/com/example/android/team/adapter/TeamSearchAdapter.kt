package com.example.android.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemTeamSearchBinding
import com.example.android.team.doamin.Team
import com.example.android.team.view.TeamSearchActivity
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class TeamSearchAdapter(private val teamSearchActivity: TeamSearchActivity) : RecyclerView.Adapter<TeamSearchAdapter.ViewHolder>()  // 팀의 목록을 표시하는 어댑터, 검색에 사용
{
    private var items = mutableListOf<Team>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemTeamSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemTeamSearchBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: Team)
        {
            val teamRequireSkillAdapter = TeamRequireSkillAdapter()
            val flexBoxLayoutManager = FlexboxLayoutManager(teamSearchActivity)
            flexBoxLayoutManager.justifyContent = JustifyContent.CENTER

            binding.team = item
            binding.teamSearchActivity = teamSearchActivity
            binding.teamRequireSkillRecyclerView.apply()
            {
                adapter = teamRequireSkillAdapter
                layoutManager = flexBoxLayoutManager
            }
            teamRequireSkillAdapter.setTeamRequireSkillList(item.skill!!)
        }
    }

    fun addTeamList(items: MutableList<Team>)
    {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearTeamList()
    {
        this.items = mutableListOf()
        notifyDataSetChanged()
    }
}