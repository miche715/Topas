package com.example.android.chat.view

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseFragment
import com.example.android.chat.adapter.ChatRoomAdapter
import com.example.android.chat.domain.ChatRoom
import com.example.android.chat.viewmodel.ChatViewModel
import com.example.android.databinding.FragmentChatRoomListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatRoomListFragment : BaseFragment<FragmentChatRoomListBinding>(R.layout.fragment_chat_room_list)
{
    private val chatViewModel: ChatViewModel by activityViewModels()

    private val chatRoomAdapter: ChatRoomAdapter by lazy { ChatRoomAdapter(this@ChatRoomListFragment) }

    override fun onInitialize()
    {
        chatViewModel.loadChatRoom()

        binding!!.chatRoomRecyclerView.adapter = chatRoomAdapter
        binding!!.chatRoomRecyclerView.layoutManager = LinearLayoutManager(this@ChatRoomListFragment.context)

        chatViewModel.chatRoomResult.observe(this@ChatRoomListFragment.activity!!)
        {

            //chatRoomAdapter.clearChatRoomList()
            chatRoomAdapter.setChatRoomList(it)
        }
    }

    fun onChatRoomClick(chatRoom: ChatRoom)
    {
        Intent(this@ChatRoomListFragment.context, ChatRoomActivity::class.java).run()
        {
            this.putExtra("destinationDocumentId", chatRoom.destinationUserDocumentId)
            this.putExtra("destinationNickName", chatRoom.destinationUserNickName)
            this.putExtra("destinationProfilePhotoUri", chatRoom.destinationUserProfilePhotoUri)
            this.putExtra("chatRoom", chatRoom)
            startActivity(this)
        }
    }
}