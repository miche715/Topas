package com.example.android.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.chat.domain.Chat
import com.example.android.databinding.ItemChatExitBinding
import com.example.android.databinding.ItemChatMyBinding
import com.example.android.databinding.ItemChatYourBinding

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()  // 채팅 목록을 표시하는 어댑터
{
    private var items = mutableListOf<Chat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        return when(viewType)
        {
            0 -> MyChatViewHolder(ItemChatMyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            1 -> YourChatViewHolder(ItemChatYourBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> ExitChatViewHolder(ItemChatExitBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        when(holder)
        {
            is MyChatViewHolder -> holder.bind(items[position])
            is YourChatViewHolder -> holder.bind(items[position])
            is ExitChatViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].viewType!!

    inner class MyChatViewHolder(private val binding: ItemChatMyBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: Chat)
        {
            binding.chat = item
        }
    }

    inner class YourChatViewHolder(private val binding: ItemChatYourBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: Chat)
        {
            binding.chat = item
        }
    }

    inner class ExitChatViewHolder(private val binding: ItemChatExitBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: Chat)
        {
            binding.chat = item
        }
    }

    fun addChatList(items: MutableList<Chat>)
    {
        items.forEach()
        {
            if(it !in this.items)
            {
                this.items.add(it)
            }
        }
        notifyDataSetChanged()
    }
}