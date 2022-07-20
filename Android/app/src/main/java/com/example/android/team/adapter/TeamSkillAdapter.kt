package com.example.android.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemMemberHaveSkillBinding

class TeamSkillAdapter : RecyclerView.Adapter<TeamSkillAdapter.ViewHolder>()
{
    private var teamRequireSkillList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemMemberHaveSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(teamRequireSkillList[position])
    }

    override fun getItemCount(): Int = teamRequireSkillList.size

    inner class ViewHolder(private val binding: ItemMemberHaveSkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(skill: String)
        {
            binding.skill = skill
        }
    }

    fun setTeamRequireSkillList(teamRequireSkillList: MutableList<String>)
    {
        this.teamRequireSkillList = teamRequireSkillList
        notifyDataSetChanged()
    }
}