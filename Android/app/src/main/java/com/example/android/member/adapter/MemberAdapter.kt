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
            val flexBoxLayoutManager = if(view is Fragment)
            {
                FlexboxLayoutManager(view.context)
            }
            else
            {
                FlexboxLayoutManager(view as Activity)
            }
            flexBoxLayoutManager.justifyContent = JustifyContent.CENTER

            binding.member = item
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