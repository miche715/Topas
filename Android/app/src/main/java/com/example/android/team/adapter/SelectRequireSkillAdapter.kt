package com.example.android.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemTeamRequireSkillBinding
import com.example.android.databinding.ItemTeamSearchRequireSkillBinding
import com.example.android.team.view.TeamCreateActivity

class SelectRequireSkillAdapter(private val teamCreateActivity: TeamCreateActivity) : RecyclerView.Adapter<SelectRequireSkillAdapter.ViewHolder>()
{
    private var selectRequireSkillList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemTeamRequireSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(selectRequireSkillList[position])
    }

    override fun getItemCount(): Int = selectRequireSkillList.size

    inner class ViewHolder(private val binding: ItemTeamRequireSkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(skill: String)
        {
            binding.skill = skill
            binding.teamCreateActivity = teamCreateActivity
        }
    }

    fun setSelectRequireSkillList(searchSkillList: MutableList<String>)
    {
        this.selectRequireSkillList = searchSkillList
        notifyDataSetChanged()
    }
}