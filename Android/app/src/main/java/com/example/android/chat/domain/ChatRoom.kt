package com.example.android.chat.domain

import java.io.Serializable

data class ChatRoom(var chatRoomDocumentId: String? = null,
                    var joinUserDocumentId: List<String?>? = null,
                    var destinationUserDocumentId: String? = null,
                    var destinationUserNickName: String? = null,
                    var destinationUserProfilePhotoUri: String? = null
) : Serializable