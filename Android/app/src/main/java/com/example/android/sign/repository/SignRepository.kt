package com.example.android.sign.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android.user.domain.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SignRepository @Inject constructor()
{
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun signUpFirebase(email: String, password: String, name: String, nickName: String, _signUpResult: MutableLiveData<Any>)
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

                        // 여기서 Storage에 프로필 사진 올리는 로직을 구현.
                        // 프로필 사진이 올라가면 Storage에 저장한 프로필 사진의 URL을 가져와야 함.
                        // 그리고 밑에 user를 만들 때 "profile_photo_url" to null 이부분에 null 대신 가져온 URL을 넣음.
                        // 프로필 사진은 필수 사항이 아니므로 Storage에 미리 기본 프로필 사진을 하나 업로드 해두고, 프로필 사진 업로드에 실패하거나 쓰지 않으면 그걸 가져다 쓰자.
                        // Storage에 올라가는 프로필 사진의 이름이 겹치면 곤란하다. firebaseAuth.currentUser.email+LocalDateTime.now().toString()+.png 같은 형식으로 절대 겹치지 않게 올리자.

                        val user: Map<String, Any?> = mapOf("email" to firebaseAuth.currentUser!!.email,
                                                                            "name" to name,
                                                                            "nick_name" to nickName,
                                                                            "profile_photo_url" to null)

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
                                    // this.profilePhotoUrl = user["profile_photo_url"] as String // 여기 구현해야 함
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