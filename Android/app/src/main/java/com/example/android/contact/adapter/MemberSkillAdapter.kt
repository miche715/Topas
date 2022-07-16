package com.example.android.contact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemMemberSkillBinding

class MemberSkillAdapter : RecyclerView.Adapter<MemberSkillAdapter.ViewHolder>()
{
    private var memberSkillList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemMemberSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(memberSkillList[position])
    }

    override fun getItemCount(): Int = memberSkillList.size

    inner class ViewHolder(private val binding: ItemMemberSkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(skill: String)
        {
            binding.skill = skill
        }
    }

    fun setMemberSkillList(memberSkillList: MutableList<String>)
    {
        this.memberSkillList = memberSkillList
        notifyDataSetChanged()
    }
}