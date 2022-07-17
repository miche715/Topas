package com.example.android.contact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.contact.view.MemberSearchActivity
import com.example.android.databinding.ItemSearchMemberSkillBinding

class SearchMemberSkillAdapter(private val memberSearchActivity: MemberSearchActivity) : RecyclerView.Adapter<SearchMemberSkillAdapter.ViewHolder>()
{
    private var searchSkillList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemSearchMemberSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(searchSkillList[position])
    }

    override fun getItemCount(): Int = searchSkillList.size

    inner class ViewHolder(private val binding: ItemSearchMemberSkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(skill: String)
        {
            binding.skill = skill
            binding.memberSearchActivity = memberSearchActivity
        }
    }

    fun setSearchSkillList(searchSkillList: MutableList<String>)
    {
        this.searchSkillList = searchSkillList
        notifyDataSetChanged()
    }
}