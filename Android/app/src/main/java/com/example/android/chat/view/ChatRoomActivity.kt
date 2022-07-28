package com.example.android.chat.view

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.chat.adapter.ChatAdapter
import com.example.android.chat.domain.ChatRoom
import com.example.android.chat.viewmodel.ChatViewModel
import com.example.android.databinding.ActivityChatRoomBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatRoomActivity : BaseActivity<ActivityChatRoomBinding>(R.layout.activity_chat_room)
{
    private val chatViewModel: ChatViewModel by viewModels()

    private val chatAdapter = ChatAdapter()

    private val destinationDocumentId: String? by lazy { intent.getStringExtra("destinationDocumentId") }
    private val destinationNickName: String? by lazy { intent.getStringExtra("destinationNickName") }
    private val destinationProfilePhotoUri: String? by lazy { intent.getStringExtra("destinationProfilePhotoUri") }
    private lateinit var currentChatRoom: ChatRoom

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.chatRoomActivity = this@ChatRoomActivity
        binding.destinationNickName = destinationNickName

        binding.chatRecyclerView.adapter = chatAdapter
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this@ChatRoomActivity)

        currentChatRoom = if(intent.getSerializableExtra("chatRoom") == null)
        {
            ChatRoom()
        }
        else
        {
            intent.getSerializableExtra("chatRoom") as ChatRoom
        }

        if(currentChatRoom.chatRoomDocumentId != null)
        {
            chatViewModel.receiveInitialChat(currentChatRoom)
            chatViewModel.receiveChat(currentChatRoom)
        }

        chatViewModel.currentChatRoomResult.observe(this@ChatRoomActivity)
        {
            with(currentChatRoom)
            {
                this.chatRoomDocumentId = it.chatRoomDocumentId
                this.joinUserDocumentId = it.joinUserDocumentId
            }

            chatViewModel.receiveChat(currentChatRoom)
        }

        chatViewModel.receiveInitialChatResult.observe(this)
        {
            chatAdapter.addChatList(it)
            binding.chatRecyclerView.smoothScrollToPosition(chatAdapter.itemCount - 1)
        }

        chatViewModel.receiveChatResult.observe(this)
        {
            chatAdapter.addChatList(it)
            binding.chatRecyclerView.smoothScrollToPosition(chatAdapter.itemCount - 1)
        }
    }

    fun onChatButtonClick()
    {
        chatViewModel.sendChat(binding.chatEditText.text.toString(), currentChatRoom, destinationDocumentId, destinationNickName, destinationProfilePhotoUri)
        binding.chatEditText.text = null
    }
}