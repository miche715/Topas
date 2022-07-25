package com.example.android.chat.domain

import java.io.Serializable

data class ChatRoom(var chatRoomDocumentId: String? = null,
                    var joinUserDocumentId: List<String>? = null,
) : Serializable