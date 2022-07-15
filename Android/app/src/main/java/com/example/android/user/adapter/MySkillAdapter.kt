package com.example.android.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemMySkillBinding
import com.example.android.user.view.UserSkillFragment

class MySkillAdapter(private val userSkillFragment: UserSkillFragment) : RecyclerView.Adapter<MySkillAdapter.ViewHolder>()
{
    private var mySkillList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemMySkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(mySkillList[position])
    }

    override fun getItemCount(): Int = mySkillList.size

    inner class ViewHolder(private val binding: ItemMySkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(skill: String)
        {
            binding.skill = skill
            binding.userSkillFragment = userSkillFragment
        }
    }

    fun setMySkillList(searchSkillList: MutableList<String>)
    {
        this.mySkillList = searchSkillList
        notifyDataSetChanged()
    }
}