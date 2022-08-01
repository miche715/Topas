package com.example.android.sign.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.sign.repository.SignRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(private val signRepository: SignRepository) : ViewModel()
{
    private val emailRegex = "^[a-z0-9\\.\\-_]+@([a-z0-9\\-]+\\.)+[a-z]{2,6}$".toRegex()  // 이메일 형식
    private val passwordRegex = "^[a-zA-Z0-9!@#$%^&*()-_=+]{4,20}$".toRegex()  // 소문자 대문자 + 숫자 4 ~ 20자리
    private val nameRegex = "^[가-힣]*$".toRegex()  // 한글만
    private val nickNameRegex = "^[a-zA-Z가-힣0-9]{2,8}$".toRegex()  // 소문자, 대문자, 한글, 숫자 2 ~ 8자리

    val emailInValidMessage: MutableLiveData<String?> = MutableLiveData()
    val passwordInValidMessage: MutableLiveData<String?> = MutableLiveData()
    val passwordConfirmInValidMessage: MutableLiveData<String?> = MutableLiveData()
    val nameInValidMessage: MutableLiveData<String?> = MutableLiveData()
    val nickNameInValidMessage: MutableLiveData<String?> = MutableLiveData()

    //=======================================================================================================================================================================//
    private val _singUpGoogleCheck: MutableLiveData<Boolean> = MutableLiveData()
    val singUpGoogleCheck: LiveData<Boolean> = _singUpGoogleCheck

    fun signUpCheckGoogle(name: String, nickName: String)
    {
        var isValid = true

        if(name.isEmpty())
        {
            nameInValidMessage.value = "* 이름을 입력해 주세요."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else if(!name.matches(nameRegex))
        {
            nameInValidMessage.value = "* 잘못된 이름 형식입니다."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            nameInValidMessage.value = null
        }

        if(nickName.isEmpty())
        {
            nickNameInValidMessage.value = "* 닉네임을 입력해 주세요."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else if(!nickName.matches(nickNameRegex))
        {
            nickNameInValidMessage.value = "* 잘못된 닉네임 형식입니다."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            nickNameInValidMessage.value = null
        }

        _singUpGoogleCheck.value = isValid
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _signUpGoogleResult: MutableLiveData<Any> = MutableLiveData()
    val signUpGoogleResult: LiveData<Any> = _signUpGoogleResult

    fun signUpGoogle(name: String, nickName: String, profilePhoto: Uri?, googleSignInAccount: GoogleSignInAccount, credential: AuthCredential)
    {
        signRepository.signUpGoogleFirebase(name, nickName, profilePhoto, googleSignInAccount, credential, _signUpGoogleResult)
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _signInGoogleResult: MutableLiveData<Boolean> = MutableLiveData()
    val signInGoogleResult: LiveData<Boolean> = _signInGoogleResult

    fun signInGoogle(googleSignInAccount: GoogleSignInAccount, credential: AuthCredential)
    {
        signRepository.signInGoogleFirebase(googleSignInAccount, credential, _signInGoogleResult)
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _signUpResult: MutableLiveData<Any> = MutableLiveData()
    val signUpResult: LiveData<Any> = _signUpResult

    fun signUp(email: String, password: String, passwordConfirm: String, name: String, nickName: String, profilePhoto: Uri?)
    {
        var isValid = true

        if(email.isEmpty())
        {
            emailInValidMessage.value = "* 이메일을 입력해 주세요."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else if(!email.matches(emailRegex))
        {
            emailInValidMessage.value = "* 잘못된 이메일 형식입니다."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            emailInValidMessage.value = null
        }

        if(password.isEmpty())
        {
            passwordInValidMessage.value = "* 패스워드를 입력해 주세요."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else if(!password.matches(passwordRegex))
        {
            passwordInValidMessage.value = "* 잘못된 패스워드 형식입니다."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            passwordInValidMessage.value = null
        }

        if(passwordConfirm.isEmpty())
        {
            passwordConfirmInValidMessage.value = "* 패스워드 확인을 입력해 주세요."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else if(passwordConfirm != password)
        {
            passwordConfirmInValidMessage.value = "* 패스워드와 다릅니다."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            passwordConfirmInValidMessage.value = null
        }

        if(name.isEmpty())
        {
            nameInValidMessage.value = "* 이름을 입력해 주세요."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else if(!name.matches(nameRegex))
        {
            nameInValidMessage.value = "* 잘못된 이름 형식입니다."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            nameInValidMessage.value = null
        }

        if(nickName.isEmpty())
        {
            nickNameInValidMessage.value = "* 닉네임을 입력해 주세요."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else if(!nickName.matches(nickNameRegex))
        {
            nickNameInValidMessage.value = "* 잘못된 닉네임 형식입니다."
            _signUpResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            nickNameInValidMessage.value = null
        }

        if(isValid)
        {
            signRepository.signUpFirebase(email, password, name, nickName, profilePhoto, _signUpResult)
        }
    }
    //=======================================================================================================================================================================//

    //=======================================================================================================================================================================//
    private val _signInResult: MutableLiveData<Any> = MutableLiveData()
    val signInResult: LiveData<Any> = _signInResult
    val emailOrPasswordInValidMessage: MutableLiveData<String?> = MutableLiveData()

    fun signIn(email: String, password: String)
    {
        var isValid = true

        if(email.isEmpty() || password.isEmpty())
        {
            emailOrPasswordInValidMessage.value = "* 이메일과 패스워드를 모두 입력해주세요."
            _signInResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            emailOrPasswordInValidMessage.value = null
        }

        if(isValid)
        {
            signRepository.signInFirebase(email, password, _signInResult)
        }
    }
    //=======================================================================================================================================================================//
}