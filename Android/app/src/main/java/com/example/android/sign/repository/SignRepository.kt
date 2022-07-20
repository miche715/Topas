package com.example.android.sign.repository

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android.user.domain.User
import com.example.android.base.BaseApplication.Companion.firebaseAuth
import com.example.android.base.BaseApplication.Companion.firebaseFirestore
import com.example.android.base.BaseApplication.Companion.firebaseStorage
import com.example.android.base.BaseApplication.Companion.currentUser
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import com.google.firebase.Timestamp
import javax.inject.Inject

class SignRepository @Inject constructor()
{
    fun signUpFirebase(email: String, password: String, name: String, nickName: String, profilePhoto: Uri?, _signUpResult: MutableLiveData<Any>)
    {
        CoroutineScope(Dispatchers.IO).launch()
        {
            firebaseFirestore.collection("user").whereEqualTo("nick_name", nickName).get().addOnCompleteListener()  // user 컬렉션에 같은 닉네임이 있는지
            {querySnapshot ->
                if(querySnapshot.result.size() == 0)  // 같은 닉네임이 없다
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener()  // Auth에 회원가입 시도
                    {authResult ->
                        if(authResult.isSuccessful)  // Auth에 가입 성공
                        {
                            Log.d("*** signUpFirebase Auth에 가입 성공 ***", "${authResult.result}")

                            val profilePhotoUri = runBlocking()  // 프로필 사진을 업로드하고 그 URI을 가져오는 동안 유저 등록을 잠시 기다리도록 하기 위해 사용
                            {
                                if(profilePhoto != null)  // 사용자가 프로필 사진을 선택 했으면
                                {
                                    firebaseStorage.child("profile_photo_${email}").putFile(profilePhoto).await()  // Storage에 프로필 사진 저장
                                    firebaseStorage.child("profile_photo_${email}").downloadUrl.await().toString()  // 방금 저장한 프로필 사진을 가져옴
                                }
                                else  // 사용자가 프로필 사진을 선택 안했으면
                                {
                                    null  // null로 FireStore에 저장하고, 나중에 화면에 뿌릴때 URI가 null이면 미리 등록한 디폴트 프로필 사진을 띄움
                                }
                            }

                            val newUser: Map<String, Any?> = mapOf("email" to email,
                                                                  "name" to name,
                                                                  "nick_name" to nickName,
                                                                  "profile_photo_uri" to profilePhotoUri,
                                                                  "introduce" to "",
                                                                  "exposure" to false,
                                                                  "skill" to mutableListOf<String>(),
                                                                  "update_at" to Timestamp.now())

                            firebaseFirestore.collection("user").add(newUser).addOnCompleteListener()  // Auth에 가입은 성공 했으니까 user 컬렉션에 유저 정보를 넣음
                            {documentReference ->
                                if(documentReference.isSuccessful)  // user 컬렉션 등록 성공
                                {
                                    Log.d("*** signUpFirebase Firestore user 컬렉션에 등록 성공 ***", "${documentReference.result}")

                                    @Suppress("UNCHECKED_CAST")
                                    currentUser = User().apply()
                                    {
                                        this.documentId = documentReference.result.id
                                        this.email = newUser["email"] as String
                                        this.name = newUser["name"] as String
                                        this.nickName = newUser["nick_name"] as String
                                        this.profilePhotoUri = newUser["profile_photo_uri"]?.let()
                                        {
                                            it as String
                                        }?: kotlin.run()
                                        {
                                            null
                                        }
                                        this.introduce = newUser["introduce"] as String
                                        this.exposure = newUser["exposure"] as Boolean
                                        this.skill = newUser["skill"] as MutableList<String>
                                    }

                                    _signUpResult.value = true
                                }
                                else  // user 컬렉션 등록 실패
                                {
                                    Log.e("*** signUpFirebase Firestore user 컬렉션에 등록 실패 ***", "${documentReference.exception?.message}")

                                    _signUpResult.value = "알 수 없는 오류가 발생하여 가입에 실패했습니다."
                                }
                            }
                        }
                        else  // Auth에 가입 실패
                        {
                            Log.e("*** signUpFirebase Auth에 가입 실패 ***", "${authResult.exception?.message}")

                            _signUpResult.value = "이미 가입된 이메일 입니다."
                        }
                    }
                }
                else  // 같은 닉네임이 있다
                {
                    Log.e("*** signUpFirebase 실패 ***", "The nickname is already in use by another account.")

                    _signUpResult.value = "이미 사용중인 닉네임 입니다."
                }
            }
        }
    }

    fun signInFirebase(email: String, password: String, _signInResult: MutableLiveData<Any>)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener()  // Auth에 로그인 시도
        {authResult ->
            if(authResult.isSuccessful)  // Auth 로그인 성공
            {
                Log.d("*** signInFirebase Auth에 로그인 성공 ***", "${authResult.result}")

                firebaseFirestore.collection("user").whereEqualTo("email", email).get().addOnCompleteListener()
                {querySnapshot ->
                    @Suppress("UNCHECKED_CAST")
                    currentUser = User().apply()
                    {
                        this.documentId = querySnapshot.result.documents[0].id
                        this.email = querySnapshot.result.documents[0].data!!["email"] as String
                        this.name = querySnapshot.result.documents[0].data!!["name"] as String
                        this.nickName = querySnapshot.result.documents[0].data!!["nick_name"] as String
                        this.profilePhotoUri = querySnapshot.result.documents[0].data!!["profile_photo_uri"]?.let()
                        {
                            it as String
                        }?: kotlin.run()
                        {
                            null
                        }
                        this.introduce = querySnapshot.result.documents[0].data!!["introduce"] as String
                        this.exposure = querySnapshot.result.documents[0].data!!["exposure"] as Boolean
                        this.skill = querySnapshot.result.documents[0].data!!["skill"] as MutableList<String>
                    }

                    _signInResult.value = true
                }
            }
            else  // Auth 로그인 실패
            {
                Log.e("*** signInFirebase Auth에 로그인 실패 ***", "${authResult.exception?.message}")

                _signInResult.value = "이메일 또는 패스워드가 잘못됐습니다."
            }
        }
    }
}