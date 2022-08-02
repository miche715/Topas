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
import com.google.firebase.firestore.SetOptions

class TeamRepository @Inject constructor()
{
    //=======================================================================================================================================================================//
    /*
        * 사용: TeamContactFragment
        * 용도: 서버에 저장되어 있는 모든 팀들을 5개씩 가져옴.
    */
    private var tempLoadTeamListResult = mutableListOf<Team>()
    private lateinit var loadTeamListQuery: Query

    fun initializeLoadTeamListQuery()  // 초기 쿼리
    {
        loadTeamListQuery = firebaseFirestore
            .collection("team").orderBy("update_at", Query.Direction.DESCENDING).limit(5)
    }

    private fun nextLoadTeamListQuery(documentSnapshot: DocumentSnapshot)  // 이전 쿼리 결과에 다음 쿼리 결과를 이어 붙히기 위함, 페이지네이션
    {
        loadTeamListQuery = firebaseFirestore
            .collection("team")
            .orderBy("update_at", Query.Direction.DESCENDING).startAfter(documentSnapshot).limit(5)
    }

    fun loadTeamListFirebase(_loadTeamListResult: MutableLiveData<MutableList<Team>>)
    {
        tempLoadTeamListResult.clear()

        loadTeamListQuery
            .get()
            .addOnCompleteListener()
        { querySnapshot ->
            if(querySnapshot.result.size() > 0)
            {
                Log.i("${this.javaClass.simpleName} loadTeamListFirebase", "팀 로딩 성공")

                nextLoadTeamListQuery(querySnapshot.result.documents[querySnapshot.result.size() - 1])

                querySnapshot.result.documents.forEach()
                {documentSnapshot ->
                    @Suppress("UNCHECKED_CAST")
                    Team().apply()
                    {
                        this.teamDocumentId = documentSnapshot.id
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
                        this.skill = documentSnapshot["skill"] as MutableList<String>
                    }.run()
                    {
                        tempLoadTeamListResult.add(this)
                    }
                }
                _loadTeamListResult.value = tempLoadTeamListResult
            }
        }
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    /*
        * 사용: TeamContactFragment
        * 용도: 서버에 저장되어 있는 팀들 중 내가 만든 팀만 5개씩 가져옴.
    */
    fun initializeLoadMyTeamListQuery()  // 초기 쿼리
    {
        loadTeamListQuery = firebaseFirestore
            .collection("team")
            .whereEqualTo("leader_document_id", currentUser.documentId).orderBy("update_at", Query.Direction.DESCENDING).limit(5)
    }

    private fun nextLoadMyTeamListQuery(documentSnapshot: DocumentSnapshot)  // 이전 쿼리 결과에 다음 쿼리 결과를 이어 붙히기 위함, 페이지네이션
    {
        loadTeamListQuery = firebaseFirestore
            .collection("team")
            .whereEqualTo("leader_document_id", currentUser.documentId).orderBy("update_at", Query.Direction.DESCENDING).startAfter(documentSnapshot).limit(5)
    }

    fun loadMyTeamListFirebase(_loadTeamListResult: MutableLiveData<MutableList<Team>>)
    {
        tempLoadTeamListResult.clear()

        loadTeamListQuery
            .get()
            .addOnCompleteListener()
        { querySnapshot ->
            Log.i("${this.javaClass.simpleName} loadMyTeamListFirebase", "내가 만든 팀 로딩 성공")

            if(querySnapshot.result.size() > 0)
            {
                nextLoadMyTeamListQuery(querySnapshot.result.documents[querySnapshot.result.size() - 1])

                querySnapshot.result.documents.forEach()
                {documentSnapshot ->
                    @Suppress("UNCHECKED_CAST")
                    Team().apply()
                    {
                        this.teamDocumentId = documentSnapshot.id
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
                        this.skill = documentSnapshot["skill"] as MutableList<String>
                    }.run()
                    {
                        tempLoadTeamListResult.add(this)
                    }
                }
                _loadTeamListResult.value = tempLoadTeamListResult
            }
        }
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    /*
        * 사용: TeamSearchActivity
        * 용도: 서버에 저장되어 있는 팀들 중 내가 지정한 스킬을 필요로하는 팀들을 가져옴.
    */
    private var tempLoadTeamBySkillList = mutableListOf<Team>()

    fun loadTeamBySkillListFirebase(skill: List<String>,
                                    _loadTeamBySkillResult: MutableLiveData<MutableList<Team>>,
                                    _loadTeamBySkillErrorMessage: MutableLiveData<String?>)
    {
        tempLoadTeamBySkillList.clear()

        firebaseFirestore
            .collection("team")
            .whereArrayContainsAny("skill", skill).orderBy("update_at", Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener()
            { querySnapshot ->
                Log.i("${this.javaClass.simpleName} loadTeamBySkillListFirebase", "스킬을 필요로 하는 팀 검색 성공")

                if(querySnapshot.result.size() > 0)
                {
                    querySnapshot.result.documents.forEach()
                    { documentSnapshot ->
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
                            this.skill = documentSnapshot["skill"] as MutableList<String>
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
                else
                {
                    _loadTeamBySkillErrorMessage.value = "검색 결과가 없습니다."
                }
            }
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    /*
        * 사용: TeamCreateActivity
        * 용도: 새롭게 팀을 만들어 서버에 저장.
    */
    fun createTeamFirebase(title: String,
                           explanation: String,
                           skill: MutableList<String>?,
                           _createTeamResult: MutableLiveData<Boolean>)
    {
        val newTeam: Map<String, Any?> = mapOf("leader_document_id" to currentUser.documentId,
            "leader_nick_name" to currentUser.nickName,
            "leader_profile_photo_uri" to currentUser.profilePhotoUri,
            "title" to title,
            "explanation" to explanation,
            "skill" to skill,
            "update_at" to Timestamp.now())

        firebaseFirestore
            .collection("team")
            .add(newTeam)
            .addOnCompleteListener()  // Firestroe에 팀 등록 시도
        { documentReference ->
            if(documentReference.isSuccessful)  // 성공
            {
                Log.i("${this.javaClass.simpleName} createTeamFirebase", "Firestore에 팀 등록 성공")

                _createTeamResult.value = true
            }
        }
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    /*
        * 사용: TeamDetailActivity
        * 용도: 서버에서 팀을 삭제.
    */
    fun deleteTeamFirebase(team: Team,
                           _deleteTeamResult: MutableLiveData<Boolean>)
    {
        firebaseFirestore
            .collection("team").document(team.teamDocumentId!!)
            .delete()
            .addOnCompleteListener()
        { task ->
            if(task.isSuccessful)
            {
                Log.i("${this.javaClass.simpleName} createTeamFirebase", "Firestore에 팀 삭제 성공")

                _deleteTeamResult.value = true
            }
        }
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    /*
        * 사용: TeamModifyActivity
        * 용도: 서버에 있는 팀을 수정해 저장.
    */
    fun modifyTeamFirebase(title: String,
                           explanation: String,
                           skill: MutableList<String>?,
                           teamDocumentId: String,
                           _modifyTeamResult: MutableLiveData<Boolean>)
    {
        val updateTeam: Map<String, Any?> = mapOf("leader_nick_name" to currentUser.nickName,
                                                  "leader_profile_photo_uri" to currentUser.profilePhotoUri,
                                                  "title" to title,
                                                  "explanation" to explanation,
                                                  "skill" to skill,
                                                  "update_at" to Timestamp.now())

        firebaseFirestore
            .collection("team").document(teamDocumentId)
            .set(updateTeam, SetOptions.merge())
            .addOnCompleteListener()
        { task ->
            if(task.isSuccessful)  // 성공
            {
                Log.i("${this.javaClass.simpleName} createTeamFirebase", "Firestore에 팀 수정 성공")

                _modifyTeamResult.value = true
            }
        }
    }
    //=======================================================================================================================================================================//
}