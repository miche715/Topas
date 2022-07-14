package com.example.android.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemSearchSkillBinding

class SearchSkillAdapter : RecyclerView.Adapter<SearchSkillAdapter.ViewHolder>()
{
    private var searchSkillList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  // 아이템 레이아웃과 결합
    {
        val binding = ItemSearchSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  // View에 내용 입력
    {
        holder.bind(searchSkillList[position])
    }

    override fun getItemCount(): Int = searchSkillList.size  // 리스트 내 아이템 개수

    inner class ViewHolder(private val binding: ItemSearchSkillBinding) : RecyclerView.ViewHolder(binding.root)  // 레이아웃 내 View 연결
    {
        fun bind(skill: String)
        {
            binding.skill = skill
        }
    }

    fun setSearchSkillList(searchSkillList: MutableList<String>)
    {
        this.searchSkillList = searchSkillList
        notifyDataSetChanged()
    }
}