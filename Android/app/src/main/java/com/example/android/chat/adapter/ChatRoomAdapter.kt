package com.example.android.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.chat.domain.ChatRoom
import com.example.android.chat.view.ChatRoomListFragment
import com.example.android.databinding.ItemChatRoomBinding

class ChatRoomAdapter(private val chatRoomListFragment: ChatRoomListFragment) : RecyclerView.Adapter<ChatRoomAdapter.ViewHolder>()  // 채팅방들의 목록을 표시하는 어댑터
{
    private var items = mutableListOf<ChatRoom>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemChatRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemChatRoomBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: ChatRoom)
        {
            binding.chatRoomListFragment = chatRoomListFragment
            binding.chatRoom = item
        }
    }

    fun setChatRoomList(items: MutableList<ChatRoom>)
    {
        this.items.clear()
        items.forEach()
        {
            this.items.add(it)
        }
        notifyDataSetChanged()
    }
}