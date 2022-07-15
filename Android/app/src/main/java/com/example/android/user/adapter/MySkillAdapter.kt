package com.example.android.user.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemMySkillBinding
import com.example.android.user.view.UserSkillFragment

class MySkillAdapter(private val fragment: UserSkillFragment) : RecyclerView.Adapter<MySkillAdapter.ViewHolder>()
{
    private var mySkillList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  // 아이템 레이아웃과 결합
    {
        val binding = ItemMySkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  // View에 내용 입력
    {
        holder.bind(mySkillList[position])
    }

    override fun getItemCount(): Int = mySkillList.size  // 리스트 내 아이템 개수

    inner class ViewHolder(private val binding: ItemMySkillBinding) : RecyclerView.ViewHolder(binding.root)  // 레이아웃 내 View 연결
    {
        fun bind(skill: String)
        {
            binding.skill = skill
            binding.userSkillFragment = fragment
        }
    }

    fun setMySkillList(searchSkillList: MutableList<String>)
    {
        this.mySkillList = searchSkillList
        notifyDataSetChanged()
    }
}