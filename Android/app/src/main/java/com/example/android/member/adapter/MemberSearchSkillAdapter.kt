package com.example.android.member.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemMemberSearchSkillAllBinding
import com.example.android.member.view.MemberSearchActivity

class MemberSearchSkillAdapter(private val memberSearchActivity: MemberSearchActivity) : RecyclerView.Adapter<MemberSearchSkillAdapter.ViewHolder>()  // 검색할때 아래로 뜨는 어댑터
{
    private var items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemMemberSearchSkillAllBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemMemberSearchSkillAllBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: String)
        {
            binding.skill = item
            binding.memberSearchActivity = memberSearchActivity
        }
    }

    fun setSearchSkillList(items: MutableList<String>)
    {
        this.items = items
        notifyDataSetChanged()
    }
}