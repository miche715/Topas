package com.example.android.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemTeamRequireSkillBinding

class TeamRequireSkillAdapter : RecyclerView.Adapter<TeamRequireSkillAdapter.ViewHolder>()  // 팀 목록에 표시되는 필요 스킬
{
    private var items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemTeamRequireSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemTeamRequireSkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: String)
        {
            binding.skill = item
        }
    }

    fun setTeamRequireSkillList(items: MutableList<String>)
    {
        this.items = items
        notifyDataSetChanged()
    }
}