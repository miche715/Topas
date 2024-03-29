package com.example.android.user.domain

import java.io.Serializable

data class User(var documentId: String? = null,  // user 컬렉션에 있는 특정 user의 도큐먼트 아이디
                var email: String? = null,
                var name: String? = null,
                var nickName: String? = null,
                var profilePhotoUri: String? = null,  // Storage에 있는 프로필 사진의 식별 값
                var introduce: String? = null,
                var exposure: Boolean? = null,  // 이 사람이 팀원 구하는 화면에 노출 될건지
                var skill: MutableList<String>? = null  // 이 사람의 기술 스택
) : Serializable