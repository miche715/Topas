package com.example.android.member.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemMemberBinding
import com.example.android.member.view.MemberContactFragment
import com.example.android.user.domain.User
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MemberAdapter(private val memberContactFragment: MemberContactFragment) : RecyclerView.Adapter<MemberAdapter.ViewHolder>()  // 멤버들의 목록을 표시하는 어댑터, 일반에 사용
{
    private var items = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemMemberBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: User)
        {
            val memberHaveSkillAdapter = MemberHaveSkillAdapter()
            val flexBoxLayoutManager = FlexboxLayoutManager(memberContactFragment.context)
            flexBoxLayoutManager.justifyContent = JustifyContent.CENTER

            binding.member = item
            binding.memberContactFragment = memberContactFragment
            binding.memberSkillRecyclerView.apply()
            {
                adapter = memberHaveSkillAdapter
                layoutManager = flexBoxLayoutManager
            }
            memberHaveSkillAdapter.setMemberSkillList(item.skill!!)
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