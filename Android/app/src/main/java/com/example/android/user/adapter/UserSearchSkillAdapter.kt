package com.example.android.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.ItemUserSearchSkillAllBinding
import com.example.android.user.view.UserSkillFragment

class UserSearchSkillAdapter(private val userSkillFragment: UserSkillFragment) : RecyclerView.Adapter<UserSearchSkillAdapter.ViewHolder>()
{
    private var items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemUserSearchSkillAllBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemUserSearchSkillAllBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: String)
        {
            binding.skill = item
            binding.userSkillFragment = userSkillFragment
        }
    }

    fun setSearchSkillList(items: MutableList<String>)
    {
        this.items = items
        notifyDataSetChanged()
    }
}