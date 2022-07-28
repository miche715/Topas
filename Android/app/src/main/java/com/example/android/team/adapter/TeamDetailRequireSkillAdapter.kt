package com.example.android.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemTeamDetailRequireSkillBinding

class TeamDetailRequireSkillAdapter(private val items: MutableList<String>) : RecyclerView.Adapter<TeamDetailRequireSkillAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemTeamDetailRequireSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemTeamDetailRequireSkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: String)
        {
            binding.skill = item
        }
    }
}