package com.example.android.chat.domain

data class ChatRoom(var chatRoomDocumentId: String? = null,
                    var joinUserDocumentId: List<String>? = null,
                    var lastMessage: Chat? = null
)