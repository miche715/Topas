package com.example.android.chat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.chat.domain.Chat
import com.example.android.chat.domain.ChatRoom
import com.example.android.chat.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatRepository: ChatRepository) : ViewModel()
{
    //=======================================================================================================================================================================//
    private val _chatRoomResult: MutableLiveData<MutableList<ChatRoom>> = MutableLiveData()
    val chatRoomResult: LiveData<MutableList<ChatRoom>> = _chatRoomResult

    fun loadChatRoom()
    {
        chatRepository.loadChatRoomFirebase(_chatRoomResult)
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _currentChatRoomResult: MutableLiveData<ChatRoom> = MutableLiveData()
    val currentChatRoomResult: LiveData<ChatRoom> = _currentChatRoomResult

    fun sendChat(message: String, currentChatRoom: ChatRoom, destinationDocumentId: String?, destinationNickName: String?, destinationProfilePhotoUri: String?)
    {
        if(currentChatRoom.chatRoomDocumentId == null)  // currentChatRoom이 null이라는건 방이 막 만들어져서 아직 파이어베이스에 등록이 안되있는 상태
        {
            chatRepository.makeChatRoomAndSendChatFirebase(message, destinationDocumentId, destinationNickName, destinationProfilePhotoUri, _currentChatRoomResult)
        }
        else  // 원래 채팅을 하던 상태
        {
            chatRepository.sendChatFirebase(message, currentChatRoom.chatRoomDocumentId!!)
        }
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    fun exitChatRoom(currentChatRoom: ChatRoom)
    {
        chatRepository.exitChatRoomFirebase(currentChatRoom)
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _receiveInitialChatResult: MutableLiveData<MutableList<Chat>> = MutableLiveData()
    val receiveInitialChatResult: LiveData<MutableList<Chat>> = _receiveInitialChatResult

    fun receiveInitialChat(currentChatRoom: ChatRoom)
    {
        chatRepository.receiveInitialChatFirebase(currentChatRoom.chatRoomDocumentId!!, _receiveInitialChatResult)
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _receiveChatResult: MutableLiveData<MutableList<Chat>> = MutableLiveData()
    val receiveChatResult: LiveData<MutableList<Chat>> = _receiveChatResult

    fun receiveChat(currentChatRoom: ChatRoom)
    {
        chatRepository.receiveChatFirebase(currentChatRoom.chatRoomDocumentId!!, _receiveChatResult)
    }
    //=======================================================================================================================================================================//
}