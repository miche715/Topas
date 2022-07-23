package com.example.android.team.doamin

import java.io.Serializable

data class Team(var teamDocumentId: String? = null,
                var leaderDocumentId: String? = null,
                var leaderNickName: String? = null,
                var leaderProfilePhotoUri: String? = null,
                var title: String? = null,
                var explanation: String? = null,
                var skill: List<String>? = null
) : Serializable