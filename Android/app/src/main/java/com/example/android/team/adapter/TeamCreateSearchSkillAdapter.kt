package com.example.android.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemTeamCreateSearchSkillAllBinding
import com.example.android.team.view.TeamCreateActivity

class TeamCreateSearchSkillAdapter(private val teamCreateActivity: TeamCreateActivity) : RecyclerView.Adapter<TeamCreateSearchSkillAdapter.ViewHolder>()  // 팀 생성에서 스킬 검색
{
    private var items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemTeamCreateSearchSkillAllBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemTeamCreateSearchSkillAllBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: String)
        {
            binding.skill = item
            binding.teamCreateActivity = teamCreateActivity
        }
    }

    fun setSearchSkillList(items: MutableList<String>)
    {
        this.items = items
        notifyDataSetChanged()
    }
}