package com.example.android.chat.domain

import com.google.firebase.Timestamp

data class Chat(var userDocumentId: String? = null,
                var userNickName: String? = null,
                var userProfilePhotoUri: String? = null,
                var message: String = "",
                var timeStamp: Timestamp? = null
)