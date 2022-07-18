package com.example.android.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemTeamSearchRequireSkillBinding
import com.example.android.team.view.TeamCreateActivity

class SearchRequireSkillAdapter(private val teamCreateActivity: TeamCreateActivity) : RecyclerView.Adapter<SearchRequireSkillAdapter.ViewHolder>()
{
    private var searchSkillList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemTeamSearchRequireSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(searchSkillList[position])
    }

    override fun getItemCount(): Int = searchSkillList.size

    inner class ViewHolder(private val binding: ItemTeamSearchRequireSkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(skill: String)
        {
            binding.skill = skill
            binding.teamCreateActivity = teamCreateActivity
        }
    }

    fun setSearchSkillList(searchSkillList: MutableList<String>)
    {
        this.searchSkillList = searchSkillList
        notifyDataSetChanged()
    }
}