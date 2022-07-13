package com.example.android.user.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel()
{
    private val nameRegex = "^[가-힣]*$".toRegex()  // 한글만
    private val nickNameRegex = "^[a-zA-Z가-힣0-9]{2,8}$".toRegex()  // 소문자, 대문자, 한글, 숫자 2 ~ 8자리

    private val _updateUserResult: MutableLiveData<Any> = MutableLiveData()
    val updateUserResult: LiveData<Any> = _updateUserResult
    val nameInValidMessage: MutableLiveData<String?> = MutableLiveData()
    val nickNameInValidMessage: MutableLiveData<String?> = MutableLiveData()

    fun updateUser(name: String, nickName: String, profilePhoto: Uri?, introduce: String, isExposureChecked: Boolean)
    {
        var isValid = true

        if(name.isEmpty())
        {
            nameInValidMessage.value = "* 이름을 입력해 주세요."
            _updateUserResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else if(!name.matches(nameRegex))
        {
            nameInValidMessage.value = "* 잘못된 이름 형식입니다."
            _updateUserResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            nameInValidMessage.value = null
        }

        if(nickName.isEmpty())
        {
            nickNameInValidMessage.value = "* 닉네임을 입력해 주세요."
            _updateUserResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else if(!nickName.matches(nickNameRegex))
        {
            nickNameInValidMessage.value = "* 잘못된 닉네임 형식입니다."
            _updateUserResult.value = "입력을 다시 확인해주세요."
            isValid = false
        }
        else
        {
            nickNameInValidMessage.value = null
        }

        if(isValid)
        {
            userRepository.updateUserFirebase(name, nickName, profilePhoto, introduce, isExposureChecked, _updateUserResult)
        }
    }
}