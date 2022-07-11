package com.example.android.base

import android.app.Application
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application()  // 앱에서 전역적으로 쓰이는 속성이나 값들을 가지고 있음
{
    companion object
    {
        lateinit var sharedPreferences: SharedPreferences

        lateinit var firebaseAuth: FirebaseAuth
        lateinit var firebaseFirestore: FirebaseFirestore
        lateinit var firebaseStorage: StorageReference
    }

    override fun onCreate()
    {
        super.onCreate()

        sharedPreferences = getSharedPreferences("sharedpreferences", MODE_PRIVATE)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseStorage = FirebaseStorage.getInstance().reference
    }
}