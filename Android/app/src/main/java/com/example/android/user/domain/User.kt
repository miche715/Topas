package com.example.android.user.domain

data class User(var documentId: String? = null,  // user 컬렉션에 있는 특정 user의 도큐먼트 아이디
                var email: String? = null,
                var name: String? = null,
                var nickName: String? = null,
                var profilePhotoUrl: String? = null  // Storage에 있는 프로필 사진의 주소
)
