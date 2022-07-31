package com.example.android.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemUserHaveSkillBinding
import com.example.android.user.view.UserSkillFragment

class UserHaveSkillAdapter(private val userSkillFragment: UserSkillFragment) : RecyclerView.Adapter<UserHaveSkillAdapter.ViewHolder>()
{
    private var items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemUserHaveSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemUserHaveSkillBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: String)
        {
            binding.skill = item
            binding.userSkillFragment = userSkillFragment
        }
    }

    fun setMySkillList(items: MutableList<String>)
    {
        this.items = items
        notifyDataSetChanged()
    }
}