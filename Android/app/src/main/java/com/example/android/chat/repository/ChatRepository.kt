package com.example.android.chat.repository

import androidx.lifecycle.MutableLiveData
import com.example.android.base.BaseApplication.Companion.firebaseFirestore
import com.example.android.base.BaseApplication.Companion.currentUser
import com.example.android.chat.domain.Chat
import com.example.android.chat.domain.ChatRoom
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import javax.inject.Inject

class ChatRepository @Inject constructor()
{
    private lateinit var tempChatRoomList: MutableList<ChatRoom>

    fun loadChatRoomFirebase(_chatRoomResult: MutableLiveData<List<ChatRoom>>)
    {
        tempChatRoomList = mutableListOf()

        firebaseFirestore.collection("chat").whereArrayContainsAny("join_user_document_id", listOf(currentUser.documentId!!)).orderBy("time_stamp", Query.Direction.DESCENDING).addSnapshotListener()
        {querySnapshot, exception ->
            if(querySnapshot != null)
            {
                querySnapshot.forEach()
                {queryDocumentSnapshot ->
                    @Suppress("UNCHECKED_CAST")
                    tempChatRoomList.add(ChatRoom().apply()
                    {
                        this.chatRoomDocumentId = queryDocumentSnapshot.id
                        this.joinUserDocumentId = queryDocumentSnapshot.data["join_user_document_id"] as List<String>
                        this.destinationUserDocumentId = (queryDocumentSnapshot.data["join_user_document_id"] as List<String>).filterNot { it == currentUser.documentId }.first()
                        this.destinationUserNickName = (queryDocumentSnapshot.data["join_user_nick_name"]?.let { it as List<String>})?.filterNot { it == currentUser.nickName }?.first()
                        this.destinationUserProfilePhotoUri = (queryDocumentSnapshot.data["join_user_profile_photo_uri"]?.let { it as List<String> })?.filterNot { it == currentUser.profilePhotoUri }?.first()
                    })
                }
                _chatRoomResult.value = tempChatRoomList.toList()
            }
        }
    }

    fun makeChatRoomAndSendChatFirebase(message: String, destinationDocumentId: String?, destinationNickName: String?, destinationProfilePhotoUri: String?, _currentChatRoomResult: MutableLiveData<ChatRoom>)
    {
        val newChatRoom: Map<String, List<String?>> = mapOf("join_user_document_id" to listOf(currentUser.documentId!!, destinationDocumentId),
                                                           "join_user_nick_name" to listOf(currentUser.nickName!!, destinationNickName),
                                                           "join_user_profile_photo_uri" to listOf(currentUser.profilePhotoUri, destinationProfilePhotoUri))

        firebaseFirestore.collection("chat").add(newChatRoom).addOnCompleteListener()
        {documentReference ->
            _currentChatRoomResult.value = ChatRoom().apply()
            {
                this.chatRoomDocumentId = documentReference.result.id
                this.joinUserDocumentId = newChatRoom["join_user_document_id"]
                this.destinationUserNickName = destinationNickName
                this.destinationUserProfilePhotoUri = destinationProfilePhotoUri
            }

            sendChatFirebase(message, documentReference.result.id)
        }
    }

    fun sendChatFirebase(message: String, currentChatRoomDocumentId: String)
    {
        val newChat: Map<String, Any?> = mapOf("message" to message,
                                              "user_document_id" to currentUser.documentId!!,
                                              "user_nick_name" to currentUser.nickName!!,
                                              "user_profile_photo_uri" to currentUser.profilePhotoUri,
                                              "time_stamp" to Timestamp.now())

        firebaseFirestore.collection("chat").document(currentChatRoomDocumentId).collection("chatting").add(newChat)
        firebaseFirestore.collection("chat").document(currentChatRoomDocumentId).set(mapOf("time_stamp" to Timestamp.now()), SetOptions.merge())
    }

    private lateinit var tempReceiveChatList: MutableList<Chat>

    fun receiveInitialChatFirebase(currentChatRoomDocumentId: String, _receiveInitialChatResult: MutableLiveData<List<Chat>>)
    {
        tempReceiveChatList = mutableListOf()

        firebaseFirestore.collection("chat").document(currentChatRoomDocumentId).collection("chatting").orderBy("time_stamp", Query.Direction.ASCENDING).get().addOnCompleteListener()
        {querySnapshot ->
            querySnapshot.result.forEach()
            {
                tempReceiveChatList.add(Chat().apply()
                {
                    this.userDocumentId = it["user_document_id"] as String
                    this.userNickName = it["user_nick_name"] as String
                    this.userProfilePhotoUri = it["user_profile_photo_uri"]?.let { it as String }
                    this.message = it["message"]?.let { it as String }?: ""
                    this.timeStamp = it["time_stamp"] as Timestamp
                    this.viewType = if(this.userDocumentId == currentUser.documentId)
                    {
                        0  // 내 채팅
                    }
                    else
                    {
                        1  // 상대방 채팅
                    }
                })
            }
            _receiveInitialChatResult.value = tempReceiveChatList.toList()
        }
    }

    fun receiveChatFirebase(currentChatRoomDocumentId: String, _receiveChatResult: MutableLiveData<List<Chat>>)
    {
        tempReceiveChatList = mutableListOf()

        firebaseFirestore.collection("chat").document(currentChatRoomDocumentId).collection("chatting").orderBy("time_stamp", Query.Direction.ASCENDING).addSnapshotListener()
        {querySnapshot, exception ->
           if(querySnapshot != null)
           {
               querySnapshot.forEach()
               {
                   tempReceiveChatList.add(Chat().apply()
                   {
                       this.userDocumentId = it["user_document_id"] as String
                       this.userNickName = it["user_nick_name"] as String
                       this.userProfilePhotoUri = it["user_profile_photo_uri"]?.let { it as String }
                       this.message = it["message"]?.let { it as String }?: ""
                       this.timeStamp = it["time_stamp"] as Timestamp
                       this.viewType = if(this.userDocumentId == currentUser.documentId)
                       {
                           0  // 내 채팅
                       }
                       else
                       {
                           1  // 상대방 채팅
                       }
                   })
               }
               _receiveChatResult.value = tempReceiveChatList.toList()
           }
        }
    }
}


