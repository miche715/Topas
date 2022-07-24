package com.example.android.chat.domain

data class Chat(var userDocumentId: String? = null,
                var userNickName: String? = null,
                var userProfilePhotoUri: String? = null,
                var message: String = ""
)