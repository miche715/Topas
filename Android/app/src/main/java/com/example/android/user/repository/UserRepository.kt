package com.example.android.user.repository

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.android.base.BaseApplication.Companion.firebaseFirestore
import com.example.android.base.BaseApplication.Companion.firebaseStorage
import com.example.android.base.BaseApplication.Companion.currentUser
import com.google.firebase.Timestamp
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class UserRepository @Inject constructor()
{
    //=======================================================================================================================================================================//
    /*
        * 사용: UserSettingActivity
        * 용도: 나의 정보를 수정해서 서버에 저장.
    */
    fun updateUserFirebase(name: String,
                           nickName: String,
                           profilePhoto: Uri?,
                           introduce: String,
                           isExposureChecked: Boolean,
                           skill: MutableList<String>?,
                           _userUpdateResult: MutableLiveData<Any>)
    {
        CoroutineScope(Dispatchers.IO).launch()
        {
            firebaseFirestore
                .collection("user")
                .whereEqualTo("nick_name", nickName)
                .get()
                .addOnCompleteListener()
            { querySnapshot ->
                if(querySnapshot.result.size() == 0 || querySnapshot.result.documents[0].id == currentUser.documentId)  // 내가 닉네임을 바꿨는데 같은 사람이 없거나 혹은 닉네임을 안바꿈
                {
                    val profilePhotoUri = runBlocking()  // 프로필 사진을 업로드하고 그 URI를 가져오는 동안 유저 등록을 잠시 기다리도록 하기 위해 사용
                    {
                        if(profilePhoto != null)  // 프로필 사진이 선택됨
                        {
                            if(profilePhoto != currentUser.profilePhotoUri?.toUri())  // 넘어온 프로필 사진과 원래 프로필 사진이 다른 경우
                            {
                                firebaseStorage.child("profile_photo_${currentUser.email}").putFile(profilePhoto).await()  // 그대로 덮어씌움
                                firebaseStorage.child("profile_photo_${currentUser.email}").downloadUrl.await().toString()  // 그리고 받아옴
                            }
                            else  // 넘어온 프로필 사진과 원래 프로필 사진이 같은 경우
                            {
                                currentUser.profilePhotoUri
                            }
                        }
                        else
                        {
                            if(currentUser.profilePhotoUri != null)  // 원래 프로필 사진이 있으면 Storage에서 삭제해야 함
                            {
                                firebaseStorage.child("profile_photo_${currentUser.email}").delete().await()
                            }
                            null
                        }
                    }

                    val updateUser: Map<String, Any?> = mapOf("name" to name,
                                                              "nick_name" to nickName,
                                                              "profile_photo_uri" to profilePhotoUri,
                                                              "introduce" to introduce,
                                                              "exposure" to isExposureChecked,
                                                              "skill" to skill,
                                                              "update_at" to Timestamp.now())

                    firebaseFirestore
                        .collection("user").document(currentUser.documentId!!)
                        .set(updateUser, SetOptions.merge())
                        .addOnCompleteListener()  // 병합
                    { task ->
                        if(task.isSuccessful)  // 사용자 정보 업데이트 성공
                        {
                            Log.i("${this.javaClass.simpleName} updateUserFirebase", "유저 정보 수정 성공")

                            currentUser.name = name
                            currentUser.nickName = nickName
                            currentUser.profilePhotoUri = profilePhotoUri
                            currentUser.introduce = introduce
                            currentUser.exposure = isExposureChecked
                            currentUser.skill = skill

                            _userUpdateResult.value = true
                        }
                    }
                }
                else  // 닉네임을 바꿨는데 같은 사람이 있음
                {
                    Log.e("${this.javaClass.simpleName} updateUserFirebase", "이미 사용중인 닉네임이라 유저 정보 수정 실패")

                    _userUpdateResult.value = "이미 사용중인 닉네임 입니다."
                }
            }
        }
    }
    //=======================================================================================================================================================================//
}