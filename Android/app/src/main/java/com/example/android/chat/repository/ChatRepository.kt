package com.example.android.chat.repository

import androidx.lifecycle.MutableLiveData
import com.example.android.base.BaseApplication.Companion.firebaseFirestore
import com.example.android.base.BaseApplication.Companion.currentUser
import com.example.android.chat.domain.Chat
import com.example.android.chat.domain.ChatRoom
import com.google.firebase.Timestamp
import javax.inject.Inject

class ChatRepository @Inject constructor()
{
    fun makeChatRoomAndSendChatFirebase(message: String, destinationDocumentId: String, _currentChatRoomResult: MutableLiveData<ChatRoom>)
    {
        val newChatRoom: Map<String, List<String>> = mapOf("join_user_document_id" to listOf(currentUser!!.documentId!!, destinationDocumentId))

        firebaseFirestore.collection("chat").add(newChatRoom).addOnCompleteListener()
        {documentReference1 ->
            _currentChatRoomResult.value = ChatRoom().apply()
            {
                this.chatRoomDocumentId = documentReference1.result.id
                this.joinUserDocumentId = newChatRoom["join_user_document_id"]
            }

            val newChat: Map<String, Any> = mapOf("message" to message,
                                                  "user_document_id" to currentUser!!.documentId!!,
                                                  "user_nick_name" to currentUser!!.nickName!!,
                                                  "user_profile_photo_uri" to currentUser!!.profilePhotoUri!!,
                                                  "time_stamp" to Timestamp.now())

            firebaseFirestore.collection("chat").document(documentReference1.result.id).collection("chatting").add(newChat)
        }
    }

    fun sendChatFirebase(message: String, currentChatRoomDocumentId: String)
    {
        val newChat: Map<String, Any> = mapOf("message" to message,
                                              "user_document_id" to currentUser!!.documentId!!,
                                              "user_nick_name" to currentUser!!.nickName!!,
                                              "user_profile_photo_uri" to currentUser!!.profilePhotoUri!!,
                                              "time_stamp" to Timestamp.now())

        firebaseFirestore.collection("chat").document(currentChatRoomDocumentId).collection("chatting").add(newChat)
    }

    private lateinit var tempReceiveChatList: MutableList<Chat>
    fun receiveChatFirebase(currentChatRoomDocumentId: String, _receiveChatResult: MutableLiveData<List<Chat>>)
    {
        tempReceiveChatList = mutableListOf()

        firebaseFirestore.collection("chat").document(currentChatRoomDocumentId).collection("chatting").addSnapshotListener()
        {querySnapshot, exception ->
           if(querySnapshot != null)
           {
               querySnapshot.forEach()
               {
                   tempReceiveChatList.add(Chat().apply()
                   {
                       this.userDocumentId = it["user_document_id"] as String
                       this.userNickName = it["user_nick_name"] as String
                       this.userProfilePhotoUri = it["user_profile_photo_uri"] as String
                       this.message = it["message"] as String
                       this.timeStamp = it["time_stamp"] as Timestamp
                   })
               }
               _receiveChatResult.value = tempReceiveChatList.toList()
           }
        }
    }

}


