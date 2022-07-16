package com.example.android.contact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.contact.view.MemberContactFragment
import com.example.android.databinding.ItemMemberBinding
import com.example.android.user.domain.User
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MemberAdapter(private val memberContactFragment: MemberContactFragment) : RecyclerView.Adapter<MemberAdapter.ViewHolder>()
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
            val flexBoxLayoutManager = FlexboxLayoutManager(memberContactFragment.context)
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

    fun setMemberList(memberList: MutableList<User>)
    {
        this.memberList = memberList
        notifyDataSetChanged()
    }
}