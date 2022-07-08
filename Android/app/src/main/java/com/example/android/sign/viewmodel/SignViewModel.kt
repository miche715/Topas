package com.example.android.sign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.sign.repository.SignRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(private val signRepository: SignRepository) : ViewModel()
{
    private val _signUpResult: MutableLiveData<Boolean> = MutableLiveData()
    val signUpResult: LiveData<Boolean> = _signUpResult

    private val _signInResult: MutableLiveData<String?> = MutableLiveData()
    val signInResult: LiveData<String?> = _signInResult

    fun signUp(email: String, password: String)
    {
        signRepository.signUpFirebase(email, password, _signUpResult)
    }

    fun signIn(email: String, password: String)
    {
        signRepository.signInFirebase(email, password, _signInResult)
    }
}