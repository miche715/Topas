package com.example.android.member.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemMemberHaveSkillBinding

class MemberHaveSkillAdapter : RecyclerView.Adapter<MemberHaveSkillAdapter.ViewHolder>()  // 멤버를 보면 스킬 목록이 있다, 거기 리사이클러뷰에 쓰이는 어댑터
{
    private var items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemMemberHaveSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemMemberHaveSkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: String)
        {
            binding.skill = item
        }
    }

    fun setMemberSkillList(items: MutableList<String>)
    {
        this.items = items
        notifyDataSetChanged()
    }
}