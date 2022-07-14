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
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class UserRepository @Inject constructor()
{
    fun updateUserFirebase(name: String, nickName: String, profilePhoto: Uri?, introduce: String, isExposureChecked: Boolean, _userUpdateResult: MutableLiveData<Any>)
    {
        CoroutineScope(Dispatchers.IO).launch()
        {
            firebaseFirestore.collection("user").whereEqualTo("nick_name", nickName).get().addOnCompleteListener()
            {querySnapshot ->
                if(querySnapshot.result.size() == 0 || querySnapshot.result.documents[0].id == currentUser!!.documentId)  // 내가 닉네임을 바꿨는데 같은 사람이 없거나 || 닉네임을 안바꿈
                {
                    val profilePhotoUrl = runBlocking()  // 프로필 사진을 업로드하고 그 URL을 가져오는 동안 유저 등록을 잠시 기다리도록 하기 위해 사용
                    {
                        if(profilePhoto != null)  // 프로필 사진이 선택됨
                        {
                            if(profilePhoto != currentUser!!.profilePhotoUrl?.toUri())  // 넘어온 프로필 사진과 원래 프로필 사진이 다른 경우
                            {
                                firebaseStorage.child("profile_photo_${currentUser!!.email}").putFile(profilePhoto).await()  // 그대로 덮어씌움
                                firebaseStorage.child("profile_photo_${currentUser!!.email}").downloadUrl.await().toString()  // 그리고 받아옴
                            }
                            else  // 넘어온 프로필 사진과 원래 프로필 사진이 같은 경우
                            {
                                currentUser!!.profilePhotoUrl
                            }
                        }
                        else
                        {
                            if(currentUser!!.profilePhotoUrl != null)  // 원래 프로필 사진이 있으면 Storage에서 삭제해야 함
                            {
                                firebaseStorage.child("profile_photo_${currentUser!!.email}").delete().await()
                            }
                            null
                        }
                    }

//                    val updateUser: Map<String, Any?> = mapOf("name" to name,
//                                                           "nick_name" to nickName,
//                                                           "profile_photo_url" to profilePhotoUrl,
//                                                           "introduce" to introduce,
//                                                           "exposure" to false,
//                                                           "skill" to mutableListOf<String>())

                    val updateUser: Map<String, Any?> = mapOf("name" to name,
                                                              "nick_name" to nickName,
                                                              "profile_photo_url" to profilePhotoUrl,
                                                              "introduce" to introduce,
                                                              "exposure" to isExposureChecked)

                    firebaseFirestore.collection("user").document(currentUser!!.documentId!!).set(updateUser, SetOptions.merge()).addOnCompleteListener()  // 병합
                    {void ->
                        if(void.isSuccessful)  // 사용자 정보 업데이트 성공
                        {
                            Log.d("*** updateUserFirebase Firestore user 컬렉션에 업데이트 성공 ***", "User information update has been completed.")

                            currentUser!!.name = name
                            currentUser!!.nickName = nickName
                            currentUser!!.profilePhotoUrl = profilePhotoUrl
                            currentUser!!.introduce = introduce
                            currentUser!!.exposure = isExposureChecked

                            _userUpdateResult.value = true
                        }
                        else  // 사용자 정보 업데이트 실패
                        {
                            Log.d("*** updateUserFirebase Firestore user 컬렉션에 업데이트 실패 ***", "Failed to update user information due to an unknown error.")

                            _userUpdateResult.value = "알 수 없는 오류가 발생하여 저장에 실패했습니다."
                        }
                    }
                }
                else  // 닉네임을 바꿨는데 같은 사람이 있음
                {
                    Log.e("*** updateUserFirebase 실패 ***", "The nickname is already in use by another account.")

                    _userUpdateResult.value = "이미 사용중인 닉네임 입니다."
                }
            }
        }
    }
}