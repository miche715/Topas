package com.example.android.member.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemMemberSearchBinding
import com.example.android.member.view.MemberSearchActivity
import com.example.android.user.domain.User
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MemberSearchAdapter(private val memberSearchActivity: MemberSearchActivity) : RecyclerView.Adapter<MemberSearchAdapter.ViewHolder>()  // 멤버들의 목록을 표시하는 어댑터, 검색에 사용
{
    private var items = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemMemberSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemMemberSearchBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: User)
        {
            val memberHaveSkillAdapter = MemberHaveSkillAdapter()
            val flexBoxLayoutManager = FlexboxLayoutManager(memberSearchActivity)
            flexBoxLayoutManager.justifyContent = JustifyContent.CENTER

            binding.member = item
            binding.memberSearchActivity = memberSearchActivity
            binding.memberSkillRecyclerView.apply()
            {
                adapter = memberHaveSkillAdapter
                layoutManager = flexBoxLayoutManager
            }
            memberHaveSkillAdapter.setMemberSkillList(item.skill!!.toMutableList())
        }
    }

    fun addMemberList(items: MutableList<User>)
    {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearMemberList()
    {
        this.items = mutableListOf()
    }
}