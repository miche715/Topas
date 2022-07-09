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
    private val emailRegex = "^[a-z0-9\\.\\-_]+@([a-z0-9\\-]+\\.)+[a-z]{2,6}$".toRegex()  // 이메일 형식
    private val passwordRegex = "^[a-z0-9]{4,20}$".toRegex()  // 소문자 + 숫자 4 ~ 20자리
    private val nameRegex = "^[가-힣]*$".toRegex()  // 한글만
    private val nickNameRegex = "^[a-zA-Z가-힣0-9]{2,8}$".toRegex()  // 소문자, 대문자, 한글, 숫자 2 ~ 8자리

    val emailInValidMessage: MutableLiveData<String?> = MutableLiveData()
    val passwordInValidMessage: MutableLiveData<String?> = MutableLiveData()
    val passwordConfirmInValidMessage: MutableLiveData<String?> = MutableLiveData()
    val nameInValidMessage: MutableLiveData<String?> = MutableLiveData()
    val nickNameInValidMessage: MutableLiveData<String?> = MutableLiveData()

    private val _signUpResult: MutableLiveData<Boolean> = MutableLiveData()
    val signUpResult: LiveData<Boolean> = _signUpResult

    private val _signInResult: MutableLiveData<String?> = MutableLiveData()
    val signInResult: LiveData<String?> = _signInResult

    fun signUp(email: String, password: String, passwordConfirm: String, name: String, nickName: String)
    {
        var isValid = true

        if(email.isEmpty())
        {
            emailInValidMessage.value = "이메일을 입력해 주세요."
            isValid = false
        }
        else if(!email.matches(emailRegex))
        {
            emailInValidMessage.value = "잘못된 이메일 형식입니다."
            isValid = false
        }
        else
        {
            emailInValidMessage.value = null
        }

        if(password.isEmpty())
        {
            passwordInValidMessage.value = "패스워드를 입력해 주세요."
            isValid = false
        }
        else if(!password.matches(passwordRegex))
        {
            passwordInValidMessage.value = "잘못된 패스워드 형식입니다."
            isValid = false
        }
        else
        {
            passwordInValidMessage.value = null
        }

        if(passwordConfirm.isEmpty())
        {
            passwordConfirmInValidMessage.value = "패스워드 확인을 입력해 주세요."
            isValid = false
        }
        else if(passwordConfirm != password)
        {
            passwordConfirmInValidMessage.value = "패스워드와 다릅니다."
            isValid = false
        }
        else
        {
            passwordConfirmInValidMessage.value = null
        }

        if(name.isEmpty())
        {
            nameInValidMessage.value = "이름을 입력해 주세요."
            isValid = false
        }
        else if(!name.matches(nameRegex))
        {
            nameInValidMessage.value = "잘못된 이름 형식입니다."
            isValid = false
        }
        else
        {
            nameInValidMessage.value = null
        }

        if(nickName.isEmpty())
        {
            nickNameInValidMessage.value = "닉네임을 입력해 주세요."
            isValid = false
        }
        else if(!nickName.matches(nickNameRegex))
        {
            nickNameInValidMessage.value = "잘못된 이메일 형식입니다."
            isValid = false
        }
        else
        {
            nickNameInValidMessage.value = null
        }

        if(isValid)
        {
            signRepository.signUpFirebase(email, password, _signUpResult)
        }
    }

    fun signIn(email: String, password: String)
    {
        signRepository.signInFirebase(email, password, _signInResult)
    }
}