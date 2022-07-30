package com.example.android.chat.repository

import android.util.Log
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
    //=======================================================================================================================================================================//
    /*
        * 사용: ChatRoomListFragment
        * 용도: 내가 속해있는 채팅방들의 목록 표시. 새롭게 채팅방이 생길 때마다 자동으로 추가됨.
    */
    private val tempChatRoomList = mutableListOf<ChatRoom>()

    fun loadChatRoomFirebase(_chatRoomResult: MutableLiveData<MutableList<ChatRoom>>)
    {
        firebaseFirestore
            .collection("chat")
            .whereArrayContainsAny("join_user_document_id", listOf(currentUser.documentId!!)).orderBy("time_stamp", Query.Direction.DESCENDING)
            .addSnapshotListener()
        {querySnapshot, _ ->
            tempChatRoomList.clear()

            if(querySnapshot?.size()!! > 0)
            {
                Log.d("*** loadChatRoomFirebase ChatRoom 리스트 로딩 성공 ***", "${querySnapshot.toString()}")

                querySnapshot.forEach()
                {queryDocumentSnapshot ->
                    @Suppress("UNCHECKED_CAST")
                    val chatRoom = ChatRoom().apply()
                    {
                        this.chatRoomDocumentId = queryDocumentSnapshot.id
                        this.joinUserDocumentId = queryDocumentSnapshot.data["join_user_document_id"] as MutableList<String?>
//                        this.destinationUserDocumentId = (queryDocumentSnapshot.data["join_user_document_id"] as MutableList<String>).filterNot()
//                        {
//                            it == currentUser.documentId
//                        }.first()
                        this.destinationUserNickName = (queryDocumentSnapshot.data["join_user_nick_name"]?.let { it as MutableList<String>})?.filterNot()
                        {
                            it == currentUser.nickName
                        }?.first()
                        this.destinationUserProfilePhotoUri = (queryDocumentSnapshot.data["join_user_profile_photo_uri"]?.let { it as MutableList<String> })?.filterNot()
                        {
                            it == currentUser.profilePhotoUri
                        }?.first()
                    }

                    if(chatRoom !in tempChatRoomList)
                    {
                        tempChatRoomList.add(chatRoom)
                    }
                }
                _chatRoomResult.value = tempChatRoomList
            }
            else
            {
                Log.d("*** loadChatRoomFirebase ChatRoom 리스트 로딩 성공 했지만 채팅방이 없음 ***", "${querySnapshot.toString()}")

                _chatRoomResult.value = mutableListOf()
            }
        }
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    /*
        * 사용: ChatRoomActivity
        * 용도: 원래 있던 채팅방을 통해 채팅을 시작한 것이 아니라 멤버 및 팀 화면에서 함께하기를 통해 채팅을 시작한 경우 아직 채팅방 생성이 안되있다. 그래서 채팅방을 먼저 만들고 채팅 전송용 함수 호출.
    */
    fun makeChatRoomAndSendChatFirebase(message: String,
                                        destinationDocumentId: String?,
                                        destinationNickName: String?,
                                        destinationProfilePhotoUri: String?,
                                        _currentChatRoomResult: MutableLiveData<ChatRoom>)
    {
        val newChatRoom: Map<String, MutableList<String?>> = mapOf("join_user_document_id" to mutableListOf(currentUser.documentId!!, destinationDocumentId),
                                                                   "join_user_nick_name" to mutableListOf(currentUser.nickName!!, destinationNickName),
                                                                   "join_user_profile_photo_uri" to mutableListOf(currentUser.profilePhotoUri, destinationProfilePhotoUri))

        firebaseFirestore.collection("chat")
            .add(newChatRoom)
            .addOnCompleteListener()
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
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    /*
        * 사용: ChatRoomActivity
        * 용도: 채팅을 보냄.
    */
    fun sendChatFirebase(message: String,
                         currentChatRoomDocumentId: String)
    {
        val newChat: Map<String, Any?> = mapOf("message" to message,
                                              "user_document_id" to currentUser.documentId!!,
                                              "user_nick_name" to currentUser.nickName!!,
                                              "user_profile_photo_uri" to currentUser.profilePhotoUri,
                                              "time_stamp" to Timestamp.now())

        firebaseFirestore.collection("chat").document(currentChatRoomDocumentId).collection("chatting").add(newChat)
        firebaseFirestore.collection("chat").document(currentChatRoomDocumentId).set(mapOf("time_stamp" to Timestamp.now()), SetOptions.merge())
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    /*
        * 사용: ChatRoomActivity
        * 용도: 채팅방에서 나감.
    */
    fun exitChatRoomFirebase(currentChatRoom: ChatRoom)
    {
        if(currentChatRoom.chatRoomDocumentId != null)
        {
            val exitChat: Map<String, Any?> = mapOf("is_exit" to true,
                "message" to "${currentUser.nickName}님이 채팅방에서 나가셨습니다.",
                "user_document_id" to currentUser.documentId!!,
                "user_nick_name" to currentUser.nickName!!,
                "user_profile_photo_uri" to currentUser.profilePhotoUri,
                "time_stamp" to Timestamp.now())
            currentChatRoom.joinUserDocumentId!!.remove(currentUser.documentId)

            firebaseFirestore.collection("chat").document(currentChatRoom.chatRoomDocumentId!!).collection("chatting").add(exitChat)
            firebaseFirestore.collection("chat").document(currentChatRoom.chatRoomDocumentId!!).set(mapOf("time_stamp" to Timestamp.now()), SetOptions.merge())
            firebaseFirestore
                .collection("chat").document(currentChatRoom.chatRoomDocumentId!!)
                .set(mapOf("join_user_document_id" to currentChatRoom.joinUserDocumentId!!), SetOptions.merge())
        }
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    /*
        * 사용: ChatRoomActivity
        * 용도: 채팅방에 입장했을 때 서버에 저장되어 있는 그 채팅방의 저장된 채팅을 전부 가져옴.
    */
    private lateinit var tempReceiveChatList: MutableList<Chat>

    fun receiveInitialChatFirebase(currentChatRoomDocumentId: String,
                                   _receiveInitialChatResult: MutableLiveData<MutableList<Chat>>)
    {
        tempReceiveChatList = mutableListOf()

        firebaseFirestore
            .collection("chat").document(currentChatRoomDocumentId).collection("chatting")
            .orderBy("time_stamp", Query.Direction.ASCENDING)
            .get()
            .addOnCompleteListener()
        {querySnapshot ->
            querySnapshot.result.forEach()
            {queryDocumentSnapshot ->
                if(queryDocumentSnapshot["is_exit"]?.let { it as Boolean }?:kotlin.run { false })  // 상대방이 나감
                {
                    tempReceiveChatList.add(Chat().apply()
                    {
                        this.message = queryDocumentSnapshot["message"]?.let { it as String }?: ""
                        this.viewType = 2
                    })
                }
                else
                {
                    tempReceiveChatList.add(Chat().apply()
                    {
                        this.userDocumentId = queryDocumentSnapshot["user_document_id"] as String
                        this.userNickName = queryDocumentSnapshot["user_nick_name"] as String
                        this.userProfilePhotoUri = queryDocumentSnapshot["user_profile_photo_uri"]?.let { it as String }
                        this.message = queryDocumentSnapshot["message"]?.let { it as String }?: ""
                        this.timeStamp = queryDocumentSnapshot["time_stamp"] as Timestamp
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
            }
            _receiveInitialChatResult.value = tempReceiveChatList
        }
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    /*
        * 사용: ChatRoomActivity
        * 용도: 채팅방에 입장한 상태에서 서로 채팅을 주고받을 때 상대가 전송하거나 내가 전송한 채팅을 실시간으로 가져옴.
    */
    fun receiveChatFirebase(currentChatRoomDocumentId: String,
                            _receiveChatResult: MutableLiveData<MutableList<Chat>>)
    {
        tempReceiveChatList = mutableListOf()

        firebaseFirestore
            .collection("chat").document(currentChatRoomDocumentId).collection("chatting")
            .orderBy("time_stamp", Query.Direction.ASCENDING)
            .addSnapshotListener()
        {querySnapshot, _ ->
           if(querySnapshot != null)
           {
               querySnapshot.forEach()
               {queryDocumentSnapshot ->
                   if(queryDocumentSnapshot["is_exit"]?.let { it as Boolean }?:kotlin.run { false })  // 상대방이 나감
                   {
                       tempReceiveChatList.add(Chat().apply()
                       {
                           this.message = queryDocumentSnapshot["message"]?.let { it as String }?: ""
                           this.viewType = 2
                       })
                   }
                   else
                   {
                       tempReceiveChatList.add(Chat().apply()
                       {
                           this.userDocumentId = queryDocumentSnapshot["user_document_id"] as String
                           this.userNickName = queryDocumentSnapshot["user_nick_name"] as String
                           this.userProfilePhotoUri = queryDocumentSnapshot["user_profile_photo_uri"]?.let { it as String }
                           this.message = queryDocumentSnapshot["message"]?.let { it as String }?: ""
                           this.timeStamp = queryDocumentSnapshot["time_stamp"] as Timestamp
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
               }
               _receiveChatResult.value = tempReceiveChatList
           }
        }
    }
    //=======================================================================================================================================================================//
}