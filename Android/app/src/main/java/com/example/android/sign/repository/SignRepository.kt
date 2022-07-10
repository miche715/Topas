package com.example.android.sign.repository

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android.user.domain.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignRepository @Inject constructor()
{
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val firebaseStorage = FirebaseStorage.getInstance().reference

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

                            var profilePhotoUrl: String
                            runBlocking()  // 프로필 사진을 업로드하고 그 URL을 가져오는 동안 유저 등록을 잠시 기다리도록 하기 위해 사용
                            {
                                profilePhotoUrl = if(profilePhoto != null)  // 사용자가 프로필 사진을 선택 했으면
                                {
                                    firebaseStorage.child("profile_photo_${email}").putFile(profilePhoto).await()  // Storage에 프로필 사진 저장
                                    firebaseStorage.child("profile_photo_${email}").downloadUrl.await().toString()  // 방금 저장한 프로필 사진을 가져옴
                                }
                                else  // 사용자가 프로필 사진을 선택 안했으면
                                {
                                    firebaseStorage.child("default.png").downloadUrl.await().toString()  // 미리 저장해 둔 기본 프로필 사진을 가져옴
                                }
                            }

                            val user: Map<String, Any?> = mapOf("email" to email, "name" to name, "nick_name" to nickName, "profile_photo_url" to profilePhotoUrl)

                            firebaseFirestore.collection("user").add(user).addOnCompleteListener()  // Auth에 가입은 성공 했으니까 user 컬렉션에 유저 정보를 넣음
                            {documentReference ->
                                if(documentReference.isSuccessful)  // user 컬렉션 등록 성공
                                {
                                    Log.d("*** signUpFirebase Firestore user 컬렉션에 등록 성공 ***", "${documentReference.result}")

                                    _signUpResult.value = User().apply()
                                    {
                                        this.documentId = documentReference.result.id
                                        this.email = user["email"] as String
                                        this.name = user["name"] as String
                                        this.nickName = user["nick_name"] as String
                                        this.profilePhotoUrl = user["profile_photo_url"] as String
                                    }
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

    fun signInFirebase(email: String, password: String, _signInResult: MutableLiveData<String?>)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener()
        {
            if(it.isSuccessful)
            {
                _signInResult.value = FirebaseAuth.getInstance().uid
                Log.d("*** signIn 성공 ***", "${it.result}")
            }
            else
            {
                _signInResult.value = null
                Log.e("*** signIn 실패 ***", "${it.exception}")
            }
        }
    }
}