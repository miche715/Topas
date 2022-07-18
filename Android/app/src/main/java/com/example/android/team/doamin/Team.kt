package com.example.android.team.doamin

data class Team(var leaderDocumentId: String? = null,
                var leaderNickName: String? = null,
                var leaderProfilePhotoUri: String? = null,
                var title: String? = null,
                var explanation: String? = null,
                var skill: List<String>? = null
)