package com.example.android.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemSearchSkillBinding
import com.example.android.user.view.UserSkillFragment

class SearchSkillAdapter(private val userSkillFragment: UserSkillFragment) : RecyclerView.Adapter<SearchSkillAdapter.ViewHolder>()
{
    private var searchSkillList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemSearchSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(searchSkillList[position])
    }

    override fun getItemCount(): Int = searchSkillList.size

    inner class ViewHolder(private val binding: ItemSearchSkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(skill: String)
        {
            binding.skill = skill
            binding.userSkillFragment = userSkillFragment
        }
    }

    fun setSearchSkillList(searchSkillList: MutableList<String>)
    {
        this.searchSkillList = searchSkillList
        notifyDataSetChanged()
    }
}