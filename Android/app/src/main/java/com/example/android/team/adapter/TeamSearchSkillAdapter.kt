package com.example.android.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemTeamSearchSkillAllBinding
import com.example.android.team.view.TeamSearchActivity

class TeamSearchSkillAdapter(private val teamSearchActivity: TeamSearchActivity) : RecyclerView.Adapter<TeamSearchSkillAdapter.ViewHolder>()  // 검색할때 아래로 뜨는 어댑터
{
    private var items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemTeamSearchSkillAllBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemTeamSearchSkillAllBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: String)
        {
            binding.skill = item
            binding.teamSearchActivity = teamSearchActivity
        }
    }

    fun setSearchSkillList(items: MutableList<String>)
    {
        this.items = items
        notifyDataSetChanged()
    }
}