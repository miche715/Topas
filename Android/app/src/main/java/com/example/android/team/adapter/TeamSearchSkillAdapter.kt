package com.example.android.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemSearchTeamSkillBinding
import com.example.android.team.view.TeamSearchActivity

class TeamSearchSkillAdapter(private val teamSearchActivity: TeamSearchActivity) : RecyclerView.Adapter<TeamSearchSkillAdapter.ViewHolder>()  // 검색할때 아래로 뜨는 어댑터
{
    private var searchSkillList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemSearchTeamSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(searchSkillList[position])
    }

    override fun getItemCount(): Int = searchSkillList.size

    inner class ViewHolder(private val binding: ItemSearchTeamSkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(skill: String)
        {
            binding.skill = skill
            binding.teamSearchActivity = teamSearchActivity
        }
    }

    fun setSearchSkillList(searchSkillList: MutableList<String>)
    {
        this.searchSkillList = searchSkillList
        notifyDataSetChanged()
    }
}