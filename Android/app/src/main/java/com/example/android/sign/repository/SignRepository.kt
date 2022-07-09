package com.example.android.sign.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SignRepository @Inject constructor()
{
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun signUpFirebase(email: String, password: String, name: String, nickName: String, _signUpResult: MutableLiveData<Boolean>)
    {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener()
        {
            if(it.isSuccessful)
            {
                _signUpResult.value = true
                Log.d("*** signUp 성공 ***", "${it.result}")
            }
            else
            {
                _signUpResult.value = false
                Log.e("*** signUp 실패 ***", "${it.exception}")
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