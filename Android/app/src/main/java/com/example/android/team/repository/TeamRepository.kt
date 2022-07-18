package com.example.android.team.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import com.example.android.base.BaseApplication.Companion.firebaseFirestore
import com.example.android.base.BaseApplication.Companion.currentUser
import com.google.firebase.Timestamp

class TeamRepository @Inject constructor()
{
    fun createTeamFirebase(title: String, explanation: String, skill: List<String>?, _createTeamResult: MutableLiveData<Boolean>)
    {
        val newTeam: Map<String, Any?> = mapOf("leader_documentId" to currentUser!!.documentId,
                                               "leader_nick_name" to currentUser!!.nickName,
                                               "leader_profile_photo_uri" to currentUser!!.profilePhotoUri,
                                               "title" to title,
                                               "explanation" to explanation,
                                               "skill" to skill,
                                               "update_at" to Timestamp.now())

        firebaseFirestore.collection("team").add(newTeam).addOnCompleteListener()  // Firestroe에 팀 등록 시도
        {documentReference ->
            if(documentReference.isSuccessful)  // 성공
            {
                Log.d("*** createTeamFirebase Firestore team 컬렉션에 등록 성공 ***", "${documentReference.result}")

                _createTeamResult.value = true
            }
            else
            {
                Log.d("*** createTeamFirebase Firestore team 컬렉션에 등록 실패 ***", "${documentReference.exception?.message}")

                _createTeamResult.value = false
            }
        }
    }
}