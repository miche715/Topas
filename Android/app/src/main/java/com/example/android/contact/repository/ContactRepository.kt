package com.example.android.contact.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android.base.BaseApplication.Companion.firebaseFirestore
import com.example.android.base.BaseApplication.Companion.currentUser
import com.example.android.user.domain.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import javax.inject.Inject

class ContactRepository @Inject constructor()
{
    private var tempLoadMemberListResult = mutableListOf<User>()
    private lateinit var loadMemberListQuery: Query

    fun initializeLoadMemberListQuery()  // 초기 쿼리
    {
        loadMemberListQuery = firebaseFirestore
            .collection("user")
            .whereEqualTo("exposure", true)
            .orderBy("update_at", Query.Direction.DESCENDING)
            .limit(5)
    }

    private fun nextLoadMemberListQuery(documentSnapshot: DocumentSnapshot)  // 이전 쿼리 결과에 다음 쿼리 결과를 이어 붙히기 위함, 페이지네이션
    {
        loadMemberListQuery = firebaseFirestore.collection("user")
            .whereEqualTo("exposure", true)
            .orderBy("update_at", Query.Direction.DESCENDING)
            .startAfter(documentSnapshot)
            .limit(5)
    }

    fun loadMemberListFirebase(_loadMemberListResult: MutableLiveData<MutableList<User>>)
    {
        tempLoadMemberListResult.clear()

        loadMemberListQuery.get().addOnCompleteListener()
        {querySnapshot ->
            if(querySnapshot.result.size() > 0)  // 정보 노출을 허용한 유저가 0명 이상임
            {
                Log.d("*** loadMemberListFirebase User 리스트 로딩 성공 ***", "${querySnapshot.result}")

                nextLoadMemberListQuery(querySnapshot.result.documents[querySnapshot.result.size() - 1])

                querySnapshot.result.documents.forEach()
                {
                    if((it["email"] as String) != currentUser!!.email)  // 자기 자신은 표시하지 않음
                    {
                        @Suppress("UNCHECKED_CAST")
                        User().apply()
                        {
                            this.email = it["email"] as String
                            this.name = it["name"] as String
                            this.nickName = it["nick_name"] as String
                            this.profilePhotoUri = it["profile_photo_uri"]?.run()
                            {
                                this as String
                            }?: kotlin.run()
                            {
                                null
                            }
                            this.introduce = it["introduce"] as String
                            this.skill = it["skill"] as List<String>
                        }.run()
                        {
                            tempLoadMemberListResult.add(this)
                        }
                    }
                }
                _loadMemberListResult.value = tempLoadMemberListResult
            }
            else  // 정보 노출을 허용한 유저가 없음
            {

            }
        }
    }
}