package com.example.android.chat.view

import androidx.activity.viewModels
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.chat.domain.ChatRoom
import com.example.android.chat.viewmodel.ChatViewModel
import com.example.android.databinding.ActivityChatRoomBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatRoomActivity : BaseActivity<ActivityChatRoomBinding>(R.layout.activity_chat_room)
{
    private val chatViewModel: ChatViewModel by viewModels()

    private val destinationDocumentId: String by lazy { intent.getStringExtra("destinationDocumentId")!! }
    private val destinationNickName: String by lazy { intent.getStringExtra("destinationNickName")!! }
    private lateinit var currentChatRoom: ChatRoom

    override fun onInitialize()
    {
        setToolBar(binding.toolBar, true)

        binding.chatRoomActivity = this@ChatRoomActivity
        binding.destinationNickName = destinationNickName

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

        chatViewModel.receiveChatResult.observe(this)
        {
            println(it)
        }
    }

    fun onChatButtonClick()
    {
        chatViewModel.sendChat(binding.chatEditText.text.toString(), currentChatRoom, destinationDocumentId)
        binding.chatEditText.text = null
    }
}