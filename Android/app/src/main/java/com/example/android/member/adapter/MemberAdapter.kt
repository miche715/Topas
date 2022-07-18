package com.example.android.member.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemMemberBinding
import com.example.android.user.domain.User
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MemberAdapter(private val view: Any) : RecyclerView.Adapter<MemberAdapter.ViewHolder>()  // 멤버들의 목록을 표시하는 어댑터, 일반과 검색 둘 다 사용
{
    private var memberList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(memberList[position])
    }

    override fun getItemCount(): Int = memberList.size

    inner class ViewHolder(private val binding: ItemMemberBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(member: User)
        {
            val memberSkillAdapter = MemberSkillAdapter()
            val flexBoxLayoutManager = if(view is Fragment)
            {
                FlexboxLayoutManager(view.context)
            }
            else
            {
                FlexboxLayoutManager(view as Activity)
            }
            flexBoxLayoutManager.justifyContent = JustifyContent.CENTER

            binding.member = member
            binding.memberSkillRecyclerView.apply()
            {
                adapter = memberSkillAdapter
                layoutManager = flexBoxLayoutManager
            }
            memberSkillAdapter.setMemberSkillList(member.skill!!.toMutableList())
        }
    }

    fun addMemberList(memberList: MutableList<User>)
    {
        this.memberList.addAll(memberList)
        notifyDataSetChanged()
    }

    fun clearMemberList()
    {
        this.memberList = mutableListOf()
    }
}