package com.example.android.team.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import com.example.android.base.BaseApplication.Companion.firebaseFirestore
import com.example.android.base.BaseApplication.Companion.currentUser
import com.example.android.team.doamin.Team
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query

class TeamRepository @Inject constructor()
{
    private var tempLoadTeamListResult = mutableListOf<Team>()
    private lateinit var loadTeamListQuery: Query

    fun initializeLoadTeamListQuery()  // 초기 쿼리
    {
        loadTeamListQuery = firebaseFirestore
            .collection("team")
            .orderBy("update_at", Query.Direction.DESCENDING)
            .limit(5)
    }

    private fun nextLoadTeamListQuery(documentSnapshot: DocumentSnapshot)  // 이전 쿼리 결과에 다음 쿼리 결과를 이어 붙히기 위함, 페이지네이션
    {
        loadTeamListQuery = firebaseFirestore.collection("team")
            .orderBy("update_at", Query.Direction.DESCENDING)
            .startAfter(documentSnapshot)
            .limit(5)
    }

    fun loadTeamListFirebase(_loadTeamListResult: MutableLiveData<MutableList<Team>>)
    {
        tempLoadTeamListResult.clear()

        loadTeamListQuery.get().addOnCompleteListener()
        {querySnapshot ->
            if(querySnapshot.result.size() > 0)
            {
                Log.d("*** loadTeamListFirebase Team 리스트 로딩 성공 ***", "${querySnapshot.result}")

                nextLoadTeamListQuery(querySnapshot.result.documents[querySnapshot.result.size() - 1])

                querySnapshot.result.documents.forEach()
                {documentSnapshot ->
                    @Suppress("UNCHECKED_CAST")
                    Team().apply()
                    {
                        this.leaderDocumentId = documentSnapshot["leader_document_id"] as String
                        this.leaderNickName = documentSnapshot["leader_nick_name"] as String
                        this.leaderProfilePhotoUri = documentSnapshot["leader_profile_photo_uri"]?.let()
                        {
                            it as String
                        }?: kotlin.run()
                        {
                            null
                        }
                        this.title = documentSnapshot["title"] as String
                        this.explanation = documentSnapshot["explanation"] as String
                        this.skill = documentSnapshot["skill"] as List<String>
                    }.run()
                    {
                        tempLoadTeamListResult.add(this)
                    }
                }
                _loadTeamListResult.value = tempLoadTeamListResult
            }
//            else
//            {
//
//            }
        }
    }

    fun createTeamFirebase(title: String, explanation: String, skill: List<String>?, _createTeamResult: MutableLiveData<Boolean>)
    {
        val newTeam: Map<String, Any?> = mapOf("leader_document_id" to currentUser!!.documentId,
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

    private var tempLoadTeamBySkillList = mutableListOf<Team>()

    fun loadTeamBySkillListFirebase(skill: List<String>, _loadTeamBySkillResult: MutableLiveData<MutableList<Team>>, _loadTeamBySkillErrorMessage: MutableLiveData<String?>)
    {
        tempLoadTeamBySkillList.clear()

        firebaseFirestore.collection("team")
            .whereArrayContainsAny("skill", skill)
            .orderBy("update_at", Query.Direction.DESCENDING).get()
            .addOnCompleteListener()
            {querySnapshot ->
                if(querySnapshot.result.size() > 0)  // 정보 노출을 허용한 유저가 0명 이상임
                {
                    Log.d("*** loadTeamBySkillListFirebase Team 리스트 로딩 성공 ***", "${querySnapshot.result}")

                    querySnapshot.result.documents.forEach()
                    {documentSnapshot ->
                        @Suppress("UNCHECKED_CAST")
                        Team().apply()
                        {
                            this.leaderDocumentId = documentSnapshot["leader_document_id"] as String
                            this.leaderNickName = documentSnapshot["leader_nick_name"] as String
                            this.leaderProfilePhotoUri = documentSnapshot["leader_profile_photo_uri"]?.let()
                            {
                                it as String
                            }?: kotlin.run()
                            {
                                null
                            }
                            this.title = documentSnapshot["title"] as String
                            this.explanation = documentSnapshot["explanation"] as String
                            this.skill = documentSnapshot["skill"] as List<String>
                        }.run()
                        {
                            tempLoadTeamBySkillList.add(this)
                        }

                    }
                    _loadTeamBySkillResult.value = tempLoadTeamBySkillList
                    _loadTeamBySkillErrorMessage.value = null

                    if(_loadTeamBySkillResult.value!!.size == 0)
                    {
                        _loadTeamBySkillErrorMessage.value = "검색 결과가 없습니다."
                    }
                }
                else  // 정보 노출을 허용한 유저가 없음
                {
                    _loadTeamBySkillErrorMessage.value = "검색 결과가 없습니다."
                }
            }
    }
}