package com.example.android.member.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemMemberSkillBinding

class MemberSkillAdapter : RecyclerView.Adapter<MemberSkillAdapter.ViewHolder>()  // 멤버를 보면 스킬 목록이 있다, 거기 리사이클러뷰에 쓰이는 어댑터
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